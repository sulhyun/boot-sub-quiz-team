package kr.spring.boot.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FriendVO {

	private int fr_num;			// 친구 기본키
	private String mb_id;		// 요청 아이디
	private String fr_id;		// 요청 받은 아이디
	private String fr_status;	// 요청 상태
}
