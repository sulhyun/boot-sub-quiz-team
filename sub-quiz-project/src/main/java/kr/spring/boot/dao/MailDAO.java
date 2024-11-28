package kr.spring.boot.dao;

import org.apache.ibatis.annotations.Param;

public interface MailDAO {

	boolean insertMailCode(@Param("evc_email")String evc_email, @Param("code")String code);

}
