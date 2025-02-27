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
import kr.spring.boot.service.CommunityService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/community")
public class CommunityController {

	@Autowired
	private CommunityService communityService;
	
	@GetMapping("/list")
	public String communityList(Model model) {
		List<CommunityVO> list = communityService.getCommunityList();
		model.addAttribute("list", list);
		return "community/list";		
	} // 커뮤니티 리스트 화면
	
	@GetMapping("/post/list/{co_num}")
	public String postList(Model model, @PathVariable int co_num) {
		List<PostVO> list = communityService.getPostList(co_num);
		model.addAttribute("list", list);
		return "community/post/list";		
	} // 게시글 리스트 화면
	
	@GetMapping("/post/detail/{co_num}/{po_num}")
	public String detailList(Model model) {

		return "community/post/detail";
	} // 게시글 상세
	
}
