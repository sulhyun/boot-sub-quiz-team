package kr.spring.boot.handler;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFailHandler implements AuthenticationFailureHandler  {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		String errMsg = "";
		
		if(exception instanceof UsernameNotFoundException) {
			errMsg = "아이디 또는 비밀번호가 일치하지 않습니다.";
			String encodingErrMsg = URLEncoder.encode(errMsg, "UTF-8");
			response.sendRedirect("/member/login?error=true&msg=" + encodingErrMsg);
		}
		if(exception instanceof BadCredentialsException) {
			errMsg = "병신ㅋ";
			String encodingErrMsg = URLEncoder.encode(errMsg, "UTF-8");
			response.sendRedirect("/member/login?error=true&msg=" + encodingErrMsg);
		}
	}

}
