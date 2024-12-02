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
		System.out.println("로그인 실패 핸들러 작동중");
		String errMsg = "";
		
		if(exception instanceof UsernameNotFoundException) {
			System.out.println("UsernameNotFoundException 동작");
			errMsg = exception.getMessage();
			String encodingErrMsg = URLEncoder.encode(errMsg, "UTF-8");
			response.sendRedirect("/member/login?error=true&msg=" + encodingErrMsg);
		}
		if(exception instanceof BadCredentialsException) {
			System.out.println("BadCredentialsException 동작");
			errMsg = "아이디 또는 비밀번호가 일치하지 않습니다.";
			String encodingErrMsg = URLEncoder.encode(errMsg, "UTF-8");
			response.sendRedirect("/member/login?error=true&msg=" + encodingErrMsg);
		}
	}

}
