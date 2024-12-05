package kr.spring.boot.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {

	private String username;			// 회원 아이디
	private String password;			// 회원 비밀번호
}
