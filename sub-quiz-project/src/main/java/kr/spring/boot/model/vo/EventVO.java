package kr.spring.boot.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventVO {

	private int ev_num;			// 이벤트 기본키
	private String ev_title;	// 이벤트 제목
	private String ev_content;	// 이벤트 내용
	private int ev_start_level;	// 참여가능 회원 시작 레벨
	private int ev_end_level;	// 참여가능 회원 끝 레벨
	private int ev_point;		// 이벤트 참여 비용(포인트)
	private Date ev_datetime;	// 이벤트 등록일
	private Date ev_start;		// 이벤트 시작일
	private Date ev_end;		// 이벤트 종료일
	private String ev_status;	// 이벤트 진행 여부
	private int ev_cnt;			// 몇번까지 이벤트 참여 가능한지?
}
