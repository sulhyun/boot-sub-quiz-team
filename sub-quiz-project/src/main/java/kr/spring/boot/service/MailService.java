package kr.spring.boot.service;

public interface MailService {

	boolean setMailCode(String mb_email, String code);

	boolean getMailCode(String mb_email, String code);

	boolean delMailCode(String mb_email);

}
