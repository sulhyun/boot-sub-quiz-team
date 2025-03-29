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
	
	public FileVO(String fi_path, String fi_ori_name, int fi_reg_num, String fi_type) {
		this.fi_path = fi_path;
		this.fi_ori_name = fi_ori_name;
		this.fi_reg_num = fi_reg_num;
		this.fi_type = fi_type;
	}
}
