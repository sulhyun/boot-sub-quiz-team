package kr.spring.boot.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kr.spring.boot.dao.MemberDAO;
import kr.spring.boot.model.util.CustomUser;
import kr.spring.boot.model.vo.MemberVO;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private MemberDAO memberDao;
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		MemberVO member = memberDao.selectMember(username);
		
		if(member == null) {
			throw new UsernameNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		if(!passwordEncoder.matches(password, member.getMb_pw())) {
			throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		if(member.getMb_out_date() != null) {
			throw new LockedException("관리자에 의해 계정이 정지되었습니다. 관리자에게 문의하세요.");
		}
		CustomUser customUser = new CustomUser(member);
		return new UsernamePasswordAuthenticationToken(customUser, password, customUser.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
