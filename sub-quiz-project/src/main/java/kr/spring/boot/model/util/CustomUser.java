package kr.spring.boot.model.util;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.spring.boot.model.vo.MemberVO;
import lombok.Data;

@Data
public class CustomUser extends User {
	
	private MemberVO member;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(MemberVO vo) {
		super(	vo.getMb_id(),
				vo.getMb_pw(), 
				Arrays.asList(new SimpleGrantedAuthority(vo.getMb_auth())));
		this.member = vo;
	}
}
