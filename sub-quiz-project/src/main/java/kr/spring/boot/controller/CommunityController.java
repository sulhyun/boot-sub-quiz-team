package kr.spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.boot.model.vo.CommunityVO;
import kr.spring.boot.service.CoummuService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/community")
public class CommunityController {

	@Autowired
	private CoummuService coummuService;
	
	// 커뮤니티 리스트업 
	@GetMapping("/commuHome")
	public String commuList(Model model) {
		List<CommunityVO> coummu = coummuService.getAllcoummu();
		model.addAttribute("coummu", coummu);
		return "/community/commuHome";		
	}
}
