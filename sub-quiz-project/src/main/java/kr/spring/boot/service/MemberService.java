package kr.spring.boot.service;

import kr.spring.boot.model.dto.SignupDTO;
import kr.spring.boot.model.vo.MemberVO;

public interface MemberService {

	MemberVO selectMember(String mb_id);

	boolean signup(SignupDTO member);

	MemberVO socialSignup(MemberVO member);

	boolean updateMember(MemberVO user);

	boolean updatePw(MemberVO user);

}
