package kr.spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.boot.model.vo.CommunityVO;
import kr.spring.boot.service.CommuService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/community")
public class CommunityController {

	@Autowired
	private CommuService commuService;
	
	// 커뮤니티 리스트업 
	@GetMapping("/commuHome")
	public String commuList(Model model) {
		List<CommunityVO> commu = commuService.getAllcoummu();
		model.addAttribute("commu", commu);
		return "/community/commuHome";		
	}
	
	// 커뮤니티 게시글 정보 리스트업 
	@GetMapping("/commuDetail")
	public String detailList(Model model) {
		//List<CommunityVO> commudetail = commuService.getAllcoummu();
		//model.addAttribute("commudetail", commudetail);
		return "/community/commuDetail";		
	}
	
	
}
