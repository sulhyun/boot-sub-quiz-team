package kr.spring.boot.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Event_prizeVO {

	private int ep_num;			// 이벤트 경품 기본키
	private String ep_prize;	// 당첨 경품
	private String mb_id;		// 회원 아이디
	private int ep_rank;		// 몇등 상품
	private int ev_num;			// 이벤트 기본키
}
