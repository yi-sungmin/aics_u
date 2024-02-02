package com.nobiz.aics_u.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String LOGIN_PAGE = "/login-page";
    private static final String[] WHITE_LIST = {"/", "login-page", "/logout", "/css/*"};
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작 : {}", requestURI);

            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 : {}", requestURI);
                HttpSession session = httpRequest.getSession(false);

                // 세션이 없거나, 요청한 페이지가 메뉴에 없을 경우에 해당하면 redirect
                if (session == null) {
                    log.info("미인증 사용자 요청 : {}", requestURI);
                    // 로그인으로 redirect
                    httpResponse.sendRedirect(LOGIN_PAGE + "?redirectURL=" + requestURI);
                    return;
                }
            }

            chain.doFilter(request, response);
            
        } catch (Exception ex) {
            throw ex;   // 예외 로깅 가능 하지만, 톰캣까지 예외를 보내주어야 함
        } finally {
            log.info("인증 체크 필터 종료 : {}", requestURI);
        }

    }
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
