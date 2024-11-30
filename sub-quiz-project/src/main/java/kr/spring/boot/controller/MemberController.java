package kr.spring.boot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.boot.model.dto.SignupDTO;
import kr.spring.boot.model.vo.MemberVO;
import kr.spring.boot.service.MemberService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private MemberService memberService;
	
	@GetMapping("/signup")
	public String signup() {
		return "member/signup";
	}
	
	@GetMapping("/checkId")
	public ResponseEntity<Boolean> checkId(String mb_id) {
		MemberVO user = memberService.selectMember(mb_id);
		boolean res = (user == null) ? true : false; 
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/signup")
	public String signupPost(SignupDTO member) {
		System.out.println("폼 정상적으로 서버 도착");
		System.out.println(member);
		System.out.println(member.getMb_hp());
		System.out.println(member.getMb_birth());
		return "redirect:/member/signup";
	}
}
