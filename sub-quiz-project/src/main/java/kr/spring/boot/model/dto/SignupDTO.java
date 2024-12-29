package kr.spring.boot.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupDTO {

	private String mb_id;			// 회원 아이디
	private String mb_pw;			// 회원 비밀번호
	private String mb_name;			// 회원 이름
	private String mb_nick;			// 회원 닉네임
	private String mb_hp;			// 회원 전화번호
	private String mb_email;		// 회원 이메일
	private int mb_zip;				// 회원 우편번호
	private String mb_addr;			// 회원 기본주소
	private String mb_addr2;		// 회원 상세주소
	private String mb_birth;		// 회원 생년월일
	
	// 초기값 설정
	private int mb_level = 1;		// 회원 레벨
	private int mb_point = 500;		// 회원 포인트
	private String mb_login_method = "internal";
	
	// 관리자를 통해서 회원가입 or 일반 회원가입
	private String where;
}
