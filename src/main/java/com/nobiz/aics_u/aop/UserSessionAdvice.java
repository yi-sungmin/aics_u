package com.nobiz.aics_u.aop;


import com.nobiz.aics_u.constants.Constants;
import com.nobiz.aics_u.model.dto.UserSession;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserSessionAdvice {
	private final HttpSession session;
	public UserSessionAdvice(HttpSession session) {
		this.session = session;
	}
	@Before("!@annotation(com.nobiz.aics_u.annotation.Exclude) && (" +
			"@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
			"@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
			"@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
			"@annotation(org.springframework.web.bind.annotation.DeleteMapping))")
	public void beforeController(JoinPoint joinPoint) {
		for (Object obj : joinPoint.getArgs()) {
			if (obj instanceof UserSession userSession) {
				UserSession savedSession = (UserSession) session.getAttribute(Constants.LOGIN_SESSION);
				userSession.setLoginUserId(savedSession.getLoginUserId());
				userSession.setLoginUserNm(savedSession.getLoginUserNm());
				userSession.setLoginAuthId(savedSession.getLoginAuthId());
				userSession.setLoginAuthNm(savedSession.getLoginAuthNm());
			}
		}
	}
}