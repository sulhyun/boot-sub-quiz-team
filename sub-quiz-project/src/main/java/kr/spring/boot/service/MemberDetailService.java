package kr.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.spring.boot.dao.MemberDAO;
import kr.spring.boot.model.util.CustomUser;
import kr.spring.boot.model.vo.MemberVO;

@Service
public class MemberDetailService implements UserDetailsService {

	@Autowired
	MemberDAO memberDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("멤버디테일 서비스 작동중");
		MemberVO member = memberDao.selectMember(username);
	  if (member == null) {
	        throw new UsernameNotFoundException("사용자 '" + username + "'을(를) 찾을 수 없습니다.");
	    }
		return new CustomUser(member);
	}

}