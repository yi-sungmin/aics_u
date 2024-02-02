package com.nobiz.aics_u.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final String LOGIN_URL = "/login-page";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message = getMessage(exception);
        String redirectUrl = LOGIN_URL + "?hasMessage=true&message=" + message;
        setDefaultFailureUrl(redirectUrl);
        super.onAuthenticationFailure(request, response, exception);
    }

    private static String getMessage(AuthenticationException exception) throws UnsupportedEncodingException {
        String message = "아이디 또는 비밀번호를 확인해주세요.";
        return URLEncoder.encode(message, StandardCharsets.UTF_8);
    }
}
