package kr.spring.boot.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.spring.boot.model.vo.CommunityVO;
import kr.spring.boot.model.vo.PostVO;
import kr.spring.boot.pagination.PageMaker;
import kr.spring.boot.pagination.PostCriteria;
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
	} // 커뮤니티 목록 화면
	
	@GetMapping("/post/list/{co_num}")
	public String postList(Model model, @PathVariable int co_num, PostCriteria cri) {
		cri.setPerPageNum(5);
		List<PostVO> list = communityService.getPostList(cri);
		String co_name = communityService.getCommunityName(co_num);
		PageMaker pm = communityService.getPageMaker(cri);
		model.addAttribute("list", list);
		model.addAttribute("co_name", co_name);
		model.addAttribute("pm", pm);
		return "community/post/list";
	} // 게시글 목록 화면
	
	@GetMapping("/post/insert/{co_num}")
	public String postInsert(Model model, Principal principal, @PathVariable int co_num) {
		if(principal == null) {
			model.addAttribute("msg", "회원만 이용 가능합니다.");
			model.addAttribute("url", "/member/login");
			return "util/msg";
		}
		return "community/post/insert";
	} // 게시글 등록 화면
	
	@PostMapping("/post/insert")
	public String postInsertPost(Model model, Principal principal, PostVO post, MultipartFile[] fileList) {
		post.setMb_id(principal.getName());
		boolean res = communityService.addPost(post, fileList);
		if(res) {
			model.addAttribute("msg", "게시글 등록에 성공하셨습니다.");
			model.addAttribute("url", "/community/post/list/" + post.getCo_num());
		} else {
			model.addAttribute("msg", "게시글 등록에 실패하셨습니다.");
			model.addAttribute("url", "/community/post/insert/" + post.getCo_num());
		}
		return "util/msg";
	} // 게시글 등록
	
	@GetMapping("/post/detail/{co_num}/{po_num}")
	public String detailList(Model model) {
		
		return "community/post/detail";
	} // 게시글 상세
	
}
