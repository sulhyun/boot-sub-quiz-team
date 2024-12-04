package kr.spring.boot.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommunityVO {
	
	// 커뮤니티
	private int co_num;		// 커뮤니티 기본키
	private String co_name;	// 커뮤니티 명
	
	// 게시글
	private int po_num; // 게시글 번호
	private String po_title; // 게시글 제목
	private String po_content; // 게시글 본문 
	private String po_date;// 작성날짜 
	private int po_view;// 조회수
	private char po_secret;// 비밀글인 경우 
	private String mb_id;// 회원 아이디

	//댓글
	private int cm_num;// 회원 아이디
	private String cm_content;// 회원 아이디
	private int cm_ori_num;// 회원 아이디
	private String cm_date;// 회
	
	
	
	
}
