package kr.spring.boot.dao;

import org.apache.ibatis.annotations.Param;

import kr.spring.boot.model.vo.EmailVerificationVO;

public interface MailDAO {

	boolean insertMailCode(@Param("mb_email")String mb_email, @Param("code")String code);

	EmailVerificationVO selectMailCode(@Param("mb_email")String mb_email);

	boolean deleteMailCode(@Param("mb_email")String mb_email);

}
