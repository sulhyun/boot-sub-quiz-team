package kr.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
@Controller
@AllArgsConstructor
@RequestMapping("/event")
public class EventController {
	
	@GetMapping("/eventpage")
	public String event(Model model) {
		
		return null;		
	}
	
	@GetMapping("/addevent")
	public String addevent(Model model) {
		return null;
	}

}
