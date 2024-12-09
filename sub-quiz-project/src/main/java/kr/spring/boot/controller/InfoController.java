package kr.spring.boot.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.boot.model.vo.MemberVO;
import kr.spring.boot.service.MemberService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/info")
public class InfoController {
	
	MemberService memberService;
	
	@GetMapping("/basic")
	public String basic(Model model, Principal principal) {
		//MemberVO user = memberService.selectMember(principal.getName());
		//model.addAttribute("user", user);
		return "info/basic";
	}

}
