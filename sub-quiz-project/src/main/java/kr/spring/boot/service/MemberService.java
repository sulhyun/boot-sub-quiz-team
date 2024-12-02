package kr.spring.boot.service;

import kr.spring.boot.model.dto.LoginDTO;
import kr.spring.boot.model.dto.SignupDTO;
import kr.spring.boot.model.vo.MemberVO;

public interface MemberService {

	MemberVO selectMember(String mb_id);

	boolean signup(SignupDTO member);

	MemberVO login(LoginDTO user);

}
