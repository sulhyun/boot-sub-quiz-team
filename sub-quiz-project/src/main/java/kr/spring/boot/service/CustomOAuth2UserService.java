package kr.spring.boot.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.spring.boot.info.KakaoUserInfo;
import kr.spring.boot.info.NaverUserInfo;
import kr.spring.boot.info.OAuth2UserInfo;
import kr.spring.boot.model.util.OAuth2CustomUser;
import kr.spring.boot.model.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	@Lazy
    @Autowired
    private MemberService memberService;
    
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
    
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("소셜 로그인 작동 시작");
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		Map<String, Object> attributes = oAuth2User.getAttributes();
		log.info("getAttributes : {}", attributes);
		String provider = userRequest.getClientRegistration().getRegistrationId();
		String accessToken = userRequest.getAccessToken().getTokenValue();
		log.info("소셜 로그인: {}, AccessToken: {}", provider, accessToken);
		OAuth2UserInfo userInfo = null;
		if (provider.equals("kakao")) {
			log.info("login : {}", "Kakao Login");
            userInfo = new KakaoUserInfo(attributes);
	    } else if(provider.equals("naver")) {
	    	log.info("login : {}", "Naver Login");
	    	userInfo = new NaverUserInfo(attributes);
	    }
		String id = userInfo.getProviderId();
		MemberVO user = memberService.selectMember(id);
		if(user == null) {
			user = new MemberVO();
			user.setMb_email(userInfo.getEmail()); 		 
			user.setMb_id(userInfo.getProviderId());
			user.setMb_nick(userInfo.getName()); 
			user.setMb_level(1);
			user.setMb_point(500);
			user.setMb_login_method(provider);
            user = memberService.socialSignup(user);     
		}
		log.info("MemberVO : {}", user);
		System.out.println("소셜 로그인 작동 종료");
		return new OAuth2CustomUser(user, attributes, accessToken);
	}

}
