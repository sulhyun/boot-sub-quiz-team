package kr.spring.boot.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizTypeVO {
	
	private int qt_num;			// 퀴즈 유형 기본키
	private String qt_name;		// 퀴즈 카테고리 명
	private String qt_type;		// 퀴즈 유형 명
	private int ra_num;			// 점수 번호
	
}
