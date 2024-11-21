package kr.spring.boot.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecommendVO {

	private int re_num;			// 추천 기본키
	private String re_state;	// 추천 상태
	private String mb_id;		// 회원 아이디
	private int po_num;			// 게시글 기본키
}
