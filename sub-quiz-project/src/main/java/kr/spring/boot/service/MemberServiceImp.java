package kr.spring.boot.service;

import java.util.regex.Pattern;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.spring.boot.dao.MemberDAO;
import kr.spring.boot.model.dto.SignupDTO;
import kr.spring.boot.model.vo.MemberVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImp implements MemberService {
	
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	private MemberDAO memberDao;

	@Override
	public MemberVO selectMember(String mb_id) {
		return memberDao.selectMember(mb_id);
	}

	@Override
	public boolean signup(SignupDTO user) {
		if (user == null) {
			return false;
		}
		if (user.getMb_id() == null || user.getMb_id().trim().length() == 0) {
			return false;
		}
		if (!checkRegex(user.getMb_id(), "^\\w{6,20}$")) {
			return false;
		}
		if (user.getMb_pw() == null || user.getMb_pw().trim().length() == 0) {
			return false;
		}
		if (!checkRegex(user.getMb_pw(), "^[a-zA-Z0-9!@#$]{6,20}$")) {
			return false;
		}
		if (user.getMb_hp().contains("-")) {
			String[] arr = user.getMb_hp().split("-");
			if (arr.length != 3) {
				return false;
			}
			user.setMb_hp(arr[0] + arr[1] + arr[2]);
		}
		user.setMb_pw(passwordEncoder.encode(user.getMb_pw()));
		return memberDao.signup(user);
	}

	private boolean checkRegex(String str, String regex) {
		if (str != null && Pattern.matches(regex, str)) {
			return true;
		}
		return false;
	} // 정규 표현식 검사 메소드 (맞으면 참, 틀리면 거짓)

	 @Override 
	 public MemberVO socialSignup(MemberVO user) {
		 boolean res = memberDao.socialSignup(user); 
		 if(res) { 
			 return user; 
		 } 
	 	return null; 
	 } // 소셜 로그인 첫 회원이면 자동 회원가입

	@Override
	public boolean updateMember(MemberVO user) {
		if (user == null) {
			return false;
		}
		if (user.getMb_id() == null || user.getMb_id().trim().length() == 0) {
			return false;
		}
		if (!checkRegex(user.getMb_id(), "^\\w{6,20}$")) {
			return false;
		}
		if (user.getMb_hp().contains("-")) {
			String[] arr = user.getMb_hp().split("-");
			if (arr.length != 3) {
				return false;
			}
			user.setMb_hp(arr[0] + arr[1] + arr[2]);
		}
		return memberDao.updateMember(user);
	}

	@Override
	public boolean updatePw(MemberVO user) {
		if (user.getMb_pw() == null || user.getMb_pw().trim().length() == 0) {
			return false;
		}
		if (!checkRegex(user.getMb_pw(), "^[a-zA-Z0-9!@#$]{6,20}$")) {
			return false;
		}
		user.setMb_pw(passwordEncoder.encode(user.getMb_pw()));
		return memberDao.updatePw(user);
	}
}
