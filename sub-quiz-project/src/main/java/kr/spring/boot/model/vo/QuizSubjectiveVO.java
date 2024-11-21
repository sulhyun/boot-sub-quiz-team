package kr.spring.boot.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizSubjectiveVO {

	private int qs_num;					// 주관식 퀴즈 기본키
	private String qs_content;			// 주관식 퀴즈 문제
	private String qs_answer;			// 문제 풀이
	private String qs_correct_answer;	// 주관식 퀴즈 문제
	private int qt_num;					// 퀴즈 유형 기본키
}
