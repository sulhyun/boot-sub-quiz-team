package kr.spring.boot.dao;

import org.apache.ibatis.annotations.Param;

import kr.spring.boot.model.vo.MemberVO;

public interface MemberDAO {

	MemberVO selectMember(@Param("mb_id") String mb_id);

}
