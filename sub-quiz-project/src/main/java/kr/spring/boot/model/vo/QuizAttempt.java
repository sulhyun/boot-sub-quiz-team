package kr.spring.boot.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizAttempt {

	private int qa_num;				// 퀴즈 시도 기본키
	private int qa_count;			// 퀴즈 시도 횟수
	private int qa_correct_count;	// 퀴즈 맞춘 횟수
	private int qu_num;				// 퀴즈 기본키
}
