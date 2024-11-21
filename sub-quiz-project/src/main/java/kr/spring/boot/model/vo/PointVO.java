package kr.spring.boot.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointVO {
	
	private int pi_num;			// 포인트 기본키
	private int pi_no;			// 포인트 점수
	private String pi_content;	// 포인트 지급 사유
	private Date pi_datetime;	// 포인트 지급일
	private String mb_id;		// 회원 아이디
}
