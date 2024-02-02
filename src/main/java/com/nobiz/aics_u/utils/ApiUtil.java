package com.nobiz.aics_u.utils;

import com.nobiz.aics_u.exception.ApiException;
import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import javax.net.ssl.SSLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public final class ApiUtil {

	private final WebClient webClient = buildWebClient();

	private ApiUtil() {
	}

	public <T> Mono<T> get(String url, Class<T> responseType) {
		return callApi(url, "", responseType, HttpMethod.GET);
	}

	public <V, T> Mono<T> delete(String url, V request, Class<T> responseType) {
		return callApi(url, "", responseType, HttpMethod.DELETE);
	}

	public <V, T> Mono<T> post(String url, V request, Class<T> responseType) {
		return callApi(url, request, responseType, HttpMethod.POST);
	}

	public <V, T> Mono<T> put(String url, V request, Class<T> responseType) {
		return callApi(url, request, responseType, HttpMethod.PUT);
	}

	private <V, T> Mono<T> callApi(String url, V request, Class<T> responseType, HttpMethod method) {
		return webClient
			.method(method)
			.uri(url)
			.body(BodyInserters.fromValue(request))
			.retrieve()
			.bodyToMono(responseType)
			.retryWhen(Retry.backoff(3, Duration.ofSeconds(2)).jitter(0.75)
				.onRetryExhaustedThrow((spec, signal) -> {
					throw new ApiException(
						HttpStatus.INTERNAL_SERVER_ERROR,
						"Service call failed even after retrying " + signal.totalRetries() + " times");
				}));
	}

	private SslContext createSSLContext() {
		try {
			return SslContextBuilder.forClient()
				.trustManager(InsecureTrustManagerFactory.INSTANCE)
				.build();
		} catch (SSLException e) {
			throw new RuntimeException(e);
		}
	}

	private HttpClient createHttpClient() {
		return HttpClient.create()
			.secure(t -> t.sslContext(createSSLContext()))
			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
			.responseTimeout(Duration.ofMillis(5000))
			.doOnConnected(conn -> {
				conn.addHandlerLast(new ReadTimeoutHandler(5, TimeUnit.SECONDS))
					.addHandlerLast(new WriteTimeoutHandler(5, TimeUnit.SECONDS));
			});
	}

	private WebClient buildWebClient() {
		return WebClient.builder()
			.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
			.clientConnector(new ReactorClientHttpConnector(createHttpClient()))
			.filter(ExchangeFilterFunction.ofResponseProcessor(this::exchangeFilterResponseProcessor))
			.build()
			;
	}

	private Mono<ClientResponse> exchangeFilterResponseProcessor(ClientResponse response) {
		HttpStatusCode status = response.statusCode();
		if (status.isError()) {
			return response.bodyToMono(String.class)
				.flatMap(body -> Mono.error(new ApiException(status, body)));
		}

		return Mono.just(response);
	}

}
