package kr.spring.boot.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingVO {
	
	private int ra_num;		// 점수 기본키
	private int ra_no;		// 점수
	private String mb_id;	// 회원 아이디
	private int qt_num;		// 퀴즈 유형 기본키
}
