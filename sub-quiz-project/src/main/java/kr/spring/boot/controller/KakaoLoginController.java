package kr.spring.boot.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.spring.boot.model.dto.KakaoUserInfoDTO;
import kr.spring.boot.model.util.CustomUser;
import kr.spring.boot.model.vo.MemberVO;
import kr.spring.boot.service.KakaoService;
import kr.spring.boot.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/kakao")
public class KakaoLoginController {
	
	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private MemberService memberService;
	
    @GetMapping("/callback")
    public void callback(@RequestParam String code, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String accessToken = kakaoService.getAccessTokenFromKakao(code);
    	KakaoUserInfoDTO userInfo = kakaoService.getUserInfo(accessToken);
    	// User 로그인, 또는 회원가입 로직 추가
    	MemberVO user = memberService.selectMember(String.valueOf(userInfo.getId()));
    	if(user == null) {
    		user = memberService.kakaoSignup(userInfo);
    	} // 첫 로그인이면 자동 회원가입
    	   // Spring Security 컨텍스트에 인증 정보 저장
        CustomUser customUser = new CustomUser(user); // CustomUser는 UserDetails 구현체
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(customUser, "", customUser.getAuthorities());
        
        // 인증 정보 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 세션에 SecurityContext 저장 (세션이 없는 경우, 새로 생성)
        HttpSession session = request.getSession(true); // true -> 세션이 없으면 새로 생성
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        // 로그인 성공 후 메인 페이지로 리다이렉트
        response.sendRedirect("/");
    }
}
