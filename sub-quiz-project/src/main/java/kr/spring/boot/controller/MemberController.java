package kr.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.boot.service.MemberService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private MemberService memberService;
	
	@GetMapping("/signup")
	public String join() {
		
		return "member/signup";
	}
}
