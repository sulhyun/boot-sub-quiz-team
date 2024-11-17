package kr.spring.boot.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentVO {

	private int cm_num;			// 댓글 기본키
	private String cm_content;	// 댓글 내용
	private int cm_ori_num;		// 원래 댓글 번호
	private Date cm_date;		// 작성일
	private String mb_id;		// 회원 아이디
	private int po_num;			// 게시글 기본키
}
