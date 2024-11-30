package kr.spring.boot.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {

	private int mb_num;				// 회원 번호
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
	private int mb_level;			// 회원 레벨
	private Date mb_datetime;		// 회원 가입일
	private Date mb_edit_date;		// 회원	수정일
	private Date amb_out_date;		// 회원 탈퇴일
	private String mb_cookie;		// 회원 쿠키
	private String mb_cookie_limit;	// 회원 쿠키만료
	private int mb_point;			// 회원 포인트
	
	public String getMb_auth() {
		String auth = "GUEST";
		int lev = this.mb_level;
		if(lev == 4) {
			auth = "QUIZ";
		} else if(lev < 4) {
			auth = "USER";
		} else {
			auth = "ADMIN";
		}
		return auth;
	}
}
