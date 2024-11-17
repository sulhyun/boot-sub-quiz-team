package kr.spring.boot.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizVO {
	
	private int qu_num;				// 퀴즈 기본키
	private String qu_content;		// 퀴즈 문제
	private String qu_answer1;		// 보기1
	private String qu_answer2;		// 보기2
	private String qu_answer3;		// 보기3
	private String qu_answer4;		// 보기4
	private int qu_correct_answer;	// 퀴즈 정답
	private int qt_num;				// 퀴즈 유형 기본키
}
