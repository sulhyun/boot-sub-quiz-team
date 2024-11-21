package kr.spring.boot.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommunityVO {

	private int co_num;		// 커뮤니티 기본키
	private String co_name;	// 커뮤니티 명
}
