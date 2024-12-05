package kr.spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.boot.model.vo.CommunityVO;
import kr.spring.boot.model.vo.PostVO;
import kr.spring.boot.service.CommuService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/community")
public class CommunityController {

	@Autowired
	private CommuService commuService;
	
	// 커뮤니티 리스트업 
	@GetMapping("/commuPage/{co_num}")
	public String commuList(Model model, @PathVariable int co_num) {
		List<PostVO> commu = commuService.getPostList(co_num);
		model.addAttribute("commu", commu);
		return "/community/commuPage";		
	}
	
	// 커뮤니티 게시글 정보 리스트업 
	@GetMapping("/commuDetail")
	public String detailList(Model model) {

		return "/community/commuDetail";		
	}
	
	// 커뮤홈 - 카테고리 리스트업 
	@GetMapping("/commuHome")
	public String commuLisst(Model model) {
		List<CommunityVO> commu = commuService.getAllcoummu();
		model.addAttribute("commu", commu);
		return "/community/commuHome";		
	}
	
}
