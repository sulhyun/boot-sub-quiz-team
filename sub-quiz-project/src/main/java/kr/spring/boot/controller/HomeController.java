package kr.spring.boot.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.spring.boot.model.util.CustomUser;
import kr.spring.boot.model.vo.MemberVO;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model, @AuthenticationPrincipal CustomUser customUser) {
		if (customUser != null) {
			MemberVO user = customUser.getMember();
			System.out.println(user);
		}
		return "home";
	}
	
}
