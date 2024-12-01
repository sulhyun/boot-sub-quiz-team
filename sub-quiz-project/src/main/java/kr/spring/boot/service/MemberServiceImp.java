package kr.spring.boot.service;

import org.springframework.stereotype.Service;

import kr.spring.boot.dao.MemberDAO;
import kr.spring.boot.model.dto.SignupDTO;
import kr.spring.boot.model.vo.MemberVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImp implements MemberService {
	
	MemberDAO memberDao;
	
	@Override
	public MemberVO selectMember(String mb_id) {
		return memberDao.selectMember(mb_id);
	}

	@Override
	public boolean signup(SignupDTO user) {
		if(user == null) {
			return false;
		}
		
		return false;
	}

}
