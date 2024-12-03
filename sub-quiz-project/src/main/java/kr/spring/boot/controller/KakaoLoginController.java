package kr.spring.boot.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> callback(@RequestParam String code, Model model, HttpSession session, HttpServletResponse response) throws IOException {
    	String accessToken = kakaoService.getAccessTokenFromKakao(code);
    	KakaoUserInfoDTO userInfo = kakaoService.getUserInfo(accessToken);
    	System.out.println(userInfo);
    	// User 로그인, 또는 회원가입 로직 추가
    	MemberVO user = memberService.selectMember(String.valueOf(userInfo.getId()));
    	if(user == null) {
    		user = memberService.kakaoSignup(userInfo);
    	} // 첫 로그인이면 자동 회원가입
    	return new ResponseEntity<>(HttpStatus.OK);
    }
}
