package kr.spring.boot.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileVO {
	
	private int fi_num;			// 파일 기본키
	private String fi_ori_name;	// 원래 파일명
	private String fi_path;		// 파일 저장된 패스 명
	private int fi_no;			// 파일 저장 번호
	private int fi_reg_num;		// 첨부 파일을 등록한 게시물등의 기본키
	private String fi_type;		// 어느 DB에서 가져왔는지?
}
