package com.nobiz.aics_u.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;
    private final LoginSuccessHandler successHandler;
    private static final String MAIN_URL = "/main";
    private static final String LOGIN_URL = "/login-page";
    private static final String[] STATIC_RESOURCES = {"/static/**", "/plugins/**", "/css/**", "/js/**", "/image/**", "/font/**","/favicon.ico"};
    private static final String[] WHITE_LIST = {"/", "/user/**", "/user-req/**", "/logout", "/error", "/api/**"};

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // csrf admin project 에서는 disable 예정
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http
//                .csrf(AbstractHttpConfigurer::disable)
                .csrf(csrf -> csrf
                        .csrfTokenRequestHandler(requestHandler)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(WHITE_LIST)
                )
                .authorizeHttpRequests((authorizeRequests) ->
                                authorizeRequests
//                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                        .requestMatchers(MAIN_URL).permitAll()
                                        .requestMatchers(LOGIN_URL).permitAll()
                                        .requestMatchers(WHITE_LIST).permitAll()
                                        .requestMatchers(STATIC_RESOURCES).permitAll()
//                                .requestMatchers("/posts/**", "/api/v1/posts/**").hasRole(Role.USER.name())
//                                .requestMatchers("/admins/**", "/api/v1/admins/**").hasRole(Role.ADMIN.name())
                                        .anyRequest().authenticated()
                )
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/login-page")
                                .usernameParameter("userId")
                                .passwordParameter("password")
                                .loginProcessingUrl("/login/login-process")
                                .successHandler(successHandler)
                                .failureHandler(new LoginFailHandler())
                )
                .logout((logoutConfig) ->
                        logoutConfig
                                .logoutUrl("/logout")
                                .invalidateHttpSession(true) // 세션 무효화
                                .deleteCookies("JSESSIONID") // 쿠키 삭제
                                .logoutSuccessUrl(MAIN_URL + "?logout")
                                .clearAuthentication(true)
                                .permitAll()
                )
                .sessionManagement(sessionManagement -> sessionManagement      // 세션 관리 (동시 로그인 제한)
                        .invalidSessionUrl(MAIN_URL + "?invalid")
                        .maximumSessions(1)                 // 동시 접속 : 1명
                        .maxSessionsPreventsLogin(true)    // true : 로그인 제한, false(default)
                        .expiredUrl(MAIN_URL + "?hasMessage=true&message=" + URLEncoder.encode("세션이 만료되었습니다.", StandardCharsets.UTF_8)) // 세션 만료시 이동 페이지
                )
                .userDetailsService(myUserDetailsService);

        return http.build();
    }
}
