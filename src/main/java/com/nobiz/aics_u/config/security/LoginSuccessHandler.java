package com.nobiz.aics_u.config.security;

import com.nobiz.aics_u.constants.Constants;
import com.nobiz.aics_u.constants.UserStts;
import com.nobiz.aics_u.constants.UserType;
import com.nobiz.aics_u.model.dto.MenuDto;
import com.nobiz.aics_u.model.dto.UserSession;
import com.nobiz.aics_u.model.dto.login.LoginHist;
import com.nobiz.aics_u.model.dto.user.User;
import com.nobiz.aics_u.repository.mapper.LoginHistMapper;
import com.nobiz.aics_u.repository.mapper.MenuMapper;
import com.nobiz.aics_u.utils.MessageSourceUtil;
import com.nobiz.aics_u.utils.TimeUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final RequestCache requestCache = new HttpSessionRequestCache();
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private static final String DEFAULT_URL = "/clip-srch";
    private static final String LOGIN_URL = "/login-page";

    private final MenuMapper menuMapper;
    private final LoginHistMapper loginHistMapper;

    private final MessageSourceUtil ms;
    public LoginSuccessHandler(MenuMapper menuMapper, LoginHistMapper loginHistMapper, MessageSourceUtil ms) {
        this.menuMapper = menuMapper;
        this.loginHistMapper = loginHistMapper;
        this.ms = ms;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        if(session != null) {
            User user = (User) authentication.getPrincipal();
            /**
             * 임시 비밀번호 여부 확인
             * user.getTempPasswdYn().equals("Y") ? redirect : 진행
             */
            if (user.getTempPasswdYn().equals("Y")) {
                redirectStrategy.sendRedirect(request, response, (LOGIN_URL + "?tempPasswd=" + user.getUserId()));
                return;
            }

            /* 기간 만료 */
            if (user.getStts().equals(UserStts.EXPIRED.getCode())) {
                String message = URLEncoder.encode(ms.getMessage("login.stts.expired"), StandardCharsets.UTF_8);
                String redirectUrl = LOGIN_URL + "?hasMessage=true&message=" + message;
                redirectStrategy.sendRedirect(request, response, redirectUrl);
                return;
            }

            /* 정지 */
            if (user.getStts().equals(UserStts.STOP.getCode())) {
                String message = URLEncoder.encode(ms.getMessage("login.stts.stop"), StandardCharsets.UTF_8);
                String redirectUrl = LOGIN_URL + "?hasMessage=true&message=" + message;
                redirectStrategy.sendRedirect(request, response, redirectUrl);
                return;
            }

            /* 승인 대기 */
            if (user.getStts().equals(UserStts.STANDBY.getCode())) {
                String message = URLEncoder.encode(ms.getMessage("login.stts.standby"), StandardCharsets.UTF_8);
                String redirectUrl = LOGIN_URL + "?hasMessage=true&message=" + message;
                redirectStrategy.sendRedirect(request, response, redirectUrl);
                return;
            }

            log.debug("login user session info : {}", user.toString());

            String ipAddr = getIP(request);
            String loginUserId = user.getUserId();
            String currentTime = getCurrentTime();

            UserSession userSession = new UserSession();
            userSession.setLoginUserId(loginUserId);
            userSession.setLoginUserNm(user.getUserNm());
            userSession.setLoginDtm(currentTime);
            userSession.setLoginTelNo(user.getTelNo());
            userSession.setLoginEmailAddr(user.getEmailAddr());

            session.setAttribute(Constants.LOGIN_SESSION, userSession);

            String startDtm = TimeUtil.parseDate(user.getStartDtm(), "yyyyMMddHHmm", "yyyy-MM-dd");
            String endDtm = TimeUtil.parseDate(user.getEndDtm(), "yyyyMMddHHmm", "yyyy-MM-dd");

            session.setAttribute("period", (startDtm + " ~ " + endDtm));

            // 로그인 사용자 권한 메뉴 정보 담기
            List<MenuDto> menus = menuMapper.selectAllMenu();
            session.setAttribute("menus", menus);

            // 사용자 로그인 history 생성
            loginHistMapper.insertLoginHist(LoginHist.builder()
                                            .loginId(loginUserId)
                                            .ipAddr(ipAddr)
                                            .userType(UserType.USER.getCode())
                                            .build());
        }

        // 실패한 오류
        clearAuthenticationAttributes(request);

        redirectStrategy(request, response, authentication);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) return;
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    protected void redirectStrategy(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String savedRequestURI = savedRequest != null ? savedRequest.getRedirectUrl() : "";

        String targetURL = StringUtils.hasText(savedRequestURI) ? savedRequestURI : determineTargetURL(authentication);

        // Http 응답이 클라이언트로 보내졌는지 여부를 검 사한다. 보냈으면 true, 그렇지 않으면 false
        if (response.isCommitted()) {
            log.debug("Response has already been committed. Unable to redirect to " + targetURL);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetURL);
    }

    /**
     * 권한별 default URL 설정 하는 부분 (*) 나중에 필요없으면 제거
     * @param authentication
     * @return
     */
    protected String determineTargetURL(final Authentication authentication) {

        Map<String, String> defaultUrlMap = new HashMap<>();
        defaultUrlMap.put("ROLE_USER", DEFAULT_URL);
        defaultUrlMap.put("ROLE_ADMIN", DEFAULT_URL);

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if (defaultUrlMap.containsKey(authorityName)) {
                return defaultUrlMap.get(authorityName);
            }
        }

        throw new IllegalStateException();
    }

    protected String getIP(HttpServletRequest req) {
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = req.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    private String getCurrentTime() {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dayTime.format(new Date(time));
    }

}
