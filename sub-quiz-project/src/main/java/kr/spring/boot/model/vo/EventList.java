package kr.spring.boot.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventList {

	private int el_num;			// 이벤트 참여자 기본키
	private String mb_id;		// 회원 아이디
	private Date el_datetime;	// 참여 날짜
	private int ev_num;			// 이벤트 기본키
}
