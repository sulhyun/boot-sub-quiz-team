package kr.spring.boot.dao;

import org.apache.ibatis.annotations.Param;

import kr.spring.boot.model.dto.SignupDTO;
import kr.spring.boot.model.vo.MemberVO;

public interface MemberDAO {

	MemberVO selectMember(@Param("mb_id")String mb_id);

	boolean signup(@Param("user")SignupDTO user);

	boolean socialSignup(@Param("user")MemberVO user);

	boolean updateMember(@Param("user")MemberVO user);

}
