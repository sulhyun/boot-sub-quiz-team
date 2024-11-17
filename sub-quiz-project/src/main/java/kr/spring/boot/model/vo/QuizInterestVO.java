package kr.spring.boot.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizInterestVO {

	private int qi_num;		// 관심 퀴즈 기본키
	private String mb_id;	// 회원 아이디
	private int qt_num;		// 퀴즈 유형 기본키
}
