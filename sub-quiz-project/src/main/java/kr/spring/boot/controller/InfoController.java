package kr.spring.boot.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.spring.boot.model.util.CustomUtil;
import kr.spring.boot.model.vo.InquiryVO;
import kr.spring.boot.model.vo.MemberVO;
import kr.spring.boot.model.vo.PointVO;
import kr.spring.boot.pagination.Criteria;
import kr.spring.boot.pagination.PageMaker;
import kr.spring.boot.service.InfoService;
import kr.spring.boot.service.MemberService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/info")
public class InfoController {
	
	private CustomUtil customUtil;
	private MemberService memberService;
	private InfoService infoService;
	
	@GetMapping("/profile")
	public String basic(Model model, Principal principal) {
		MemberVO user = memberService.selectMember(principal.getName());
		user.setMb_hp(customUtil.autoHyphenSecret(user.getMb_hp()));
		model.addAttribute("user", user);
		return "info/profile";
	} // 회원 정보 화면
	
	@PostMapping("/profile")
	public String basicPost(RedirectAttributes redirect, Principal principal, @RequestParam Map<String, String> params) {
		boolean res = infoService.updateInfo(principal.getName(), params);
		redirect.addFlashAttribute("msg", res ? "회원 정보 수정에 성공하셨습니다." : "회원 정보 수정에 실패하였습니다.");
		return "redirect:/info/profile";
	} // 회원 정보 수정
	
	@PostMapping("/password")
	public String password(Model model, Principal principal, @RequestParam Map<String, String> params) {
		boolean res = infoService.updatePw(principal.getName(), params);
		model.addAttribute("msg", res ? "수정 성공!!" : "수정 실패!!");
		model.addAttribute("url", "/info/profile");
		return "util/msg";
	} // 비밀번호 변경
	
	@PostMapping("/cancel")
	public String cancel(Model model, Principal principal, @RequestParam Map<String, String> params, HttpServletRequest request, HttpServletResponse response) {
		boolean res = infoService.cancelMember(principal.getName(), params);
		if(res) {
			SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
	        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
		}
		model.addAttribute("msg", res ? "탈퇴 성공!!" : "탈퇴 실패!!");
		model.addAttribute("url", res ? "/member/login" : "/info/profile");
		return "util/msg";
	} // 회원 탈퇴
	
	@GetMapping("/point/{type}")
	public String point(Model model, Principal principal, Criteria cri, @PathVariable String type) {
		cri.setPerPageNum(5);
		MemberVO user = memberService.selectMember(principal.getName());
		PageMaker pm = infoService.getPageMakerByPoint(cri, principal.getName());
		List<PointVO> list = infoService.getPointList(cri, principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("pm", pm);
		model.addAttribute("list", list);
		return "info/point";
	} // 포인트 내역 화면(페이지네이션)
	
	@GetMapping("/inquiry/{type}")
	public String inquiry(Model model, Principal principal, Criteria cri, @PathVariable String type) {
		cri.setPerPageNum(10);
		PageMaker pm = infoService.getPageMakerByInquiry(cri, principal.getName());
		List<PointVO> list = infoService.getInquiryList(cri, principal.getName());
		model.addAttribute("pm", pm);
		model.addAttribute("list", list);
		return "info/inquiry";
	} // 문의 내역 화면(페이지네이션)
	
	@GetMapping("/inquiry/insert")
	public String inquiryInsert() {
		return "info/inquiry/insert";
	} // 문의 등록 화면
	
	@PostMapping("/inquiry/insert")
	public String inquiryInsertPost(Model model, Principal principal, InquiryVO inquiry) {
		inquiry.setMb_id(principal.getName());
		boolean res = infoService.addInquiry(inquiry);
		model.addAttribute("msg", res ? "문의 등록에 성공하셨습니다." : "문의 등록에 실패하셨습니다.");
		model.addAttribute("url", "/info/inquiry/mine");
		return "util/msg";
	} // 문의 등록
	
	@GetMapping("/inquiry/detail/{iq_num}")
	public String inquirtDeail(Model model, @PathVariable int iq_num) {
		InquiryVO inquiry = infoService.getInquiry(iq_num);
		model.addAttribute("inquiry", inquiry);
		return "info/inquiry/detail";
	} // 문의 상세
}
