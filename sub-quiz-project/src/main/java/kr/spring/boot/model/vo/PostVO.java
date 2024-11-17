package kr.spring.boot.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostVO {
	
	private int po_num;			// 게시글 기본키
	private String po_title;	// 게시글 제목
	private String po_content;	// 게시글 내용
	private Date po_date;		// 작성일
	private int po_view;		// 조회수
	private String po_secret;	// 비밀글
	private String mb_id;		// 회원 아이디
	private int co_num;			// 커뮤니티 기본키
}
