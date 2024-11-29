package kr.spring.boot.dao;

import org.apache.ibatis.annotations.Param;

public interface MailDAO {

	boolean insertMailCode(@Param("mb_email")String mb_email, @Param("code")String code);

}
