package kr.spring.boot.handler;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFailHandler implements AuthenticationFailureHandler  {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		String errorMessage = "";

		if (exception instanceof UsernameNotFoundException) {
			errorMessage = exception.getMessage();
			response.sendRedirect("/member/login?error=true&msg=" + URLEncoder.encode(errorMessage, "UTF-8"));
		} // 아이디 틀릴 경우
		if (exception instanceof BadCredentialsException) {
			errorMessage = exception.getMessage();
			response.sendRedirect("/member/login?error=true&msg=" + URLEncoder.encode(errorMessage, "UTF-8"));
		} // 비밀번호 틀릴 경우
		if (exception instanceof LockedException) {
			errorMessage = exception.getMessage();
			response.sendRedirect("/member/login?error=true&msg=" + URLEncoder.encode(errorMessage, "UTF-8"));
		} // 계정이 정지 됐을 경우
	}
}
