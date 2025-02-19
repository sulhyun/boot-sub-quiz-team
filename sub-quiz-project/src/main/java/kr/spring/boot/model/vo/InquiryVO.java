package kr.spring.boot.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InquiryVO {

	private int iq_num;			// 문의 기본키
	private String mb_id;		// 회원아이디
	private String iq_content;	// 문의 내용
	private Date iq_datetime1;	// 작성일
	private String iq_comment;	// 답변 내용
	private Date iq_datetime2;	// 답변일
	private int iq_view;		// 조회수
}
