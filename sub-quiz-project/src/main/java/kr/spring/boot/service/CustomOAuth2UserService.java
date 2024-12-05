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
import kr.spring.boot.info.OAuth2UserInfo;
import kr.spring.boot.model.util.OAuth2CustomUser;
import kr.spring.boot.model.vo.MemberVO;


@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	@Lazy
    @Autowired
    private MemberService memberService;
    
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
    
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// DefaultOAuth2UserService를 사용하여 소셜 제공자로부터 사용자 정보를 가져옴
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		Map<String, Object> attributes = oAuth2User.getAttributes();
		
		 // 뒤에 진행할 다른 소셜 서비스 로그인을 위해 구분 => 카카오
		String provider = userRequest.getClientRegistration().getRegistrationId();
		System.out.println(provider);
		OAuth2UserInfo userInfo = null;
		if (provider.equals("kakao")) {
	            System.out.println("카카오 로그인");
	            userInfo = new KakaoUserInfo(attributes);
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
		return new OAuth2CustomUser(user, attributes);
	}

}