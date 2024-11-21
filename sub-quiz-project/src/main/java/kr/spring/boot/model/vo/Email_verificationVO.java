package kr.spring.boot.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Email_verificationVO {

	private int evc_num;		// 이메일 인증 기본키
	private String mb_email;	// 회원 이메일
	private String evc_code;	// 인증 코드
}
