package kr.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/userquiz")
public class UserQuizController {
	
	@GetMapping("/userquizpage")
	public String userQuiz(Model model) {
		
		return "/userquiz/userquizpage";
	}

}
