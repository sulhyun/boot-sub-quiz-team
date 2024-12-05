package kr.spring.boot.model.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import kr.spring.boot.model.vo.MemberVO;

public class OAuth2CustomUser implements OAuth2User {

    private final MemberVO member; 					// 내부 사용자 정보
    private final Map<String, Object> attributes; 	// 소셜 제공자에서 받은 정보

    public OAuth2CustomUser(MemberVO member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(member.getMb_auth()));
	} // 권한 설정: MemberVO의 권한을 사용

	@Override
	public String getName() {
		return attributes.containsKey("id") ? attributes.get("id").toString() : member.getMb_id();
	} // 소셜 로그인 사용자의 고유 식별자 반환

}
