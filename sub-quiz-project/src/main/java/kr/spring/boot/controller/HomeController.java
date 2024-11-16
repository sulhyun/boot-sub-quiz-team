package kr.spring.boot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.spring.boot.dao.TestDAO;
import kr.spring.boot.model.vo.QuizTypeVO;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private TestDAO testDao;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("name", "홍길동");
		List<QuizTypeVO> list = testDao.test();
		System.out.println(list);
		return "home";
	}
	
}
