package kr.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.boot.model.dto.SignupDTO;
import kr.spring.boot.model.vo.MemberVO;
import kr.spring.boot.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/signup")
	public String signup() {
		return "member/signup";
	}
	
	@GetMapping("/checkId")
	public ResponseEntity<Boolean> checkId(String mb_id) {
		MemberVO user = memberService.selectMember(mb_id);
		boolean res = (user == null) ? true : false; 
		return ResponseEntity.ok(res);
	} // 아이디 중복검사
	
	@PostMapping("/signup")
	public String signupPost(Model model, SignupDTO user) {
		boolean res = memberService.signup(user);
		model.addAttribute("msg", res ? "회원가입에 성공하셨습니다." : "회원가입에 실패하였습니다.");
		model.addAttribute("url", user.getWhere().equals("user") ? "/" : "/admin/member/list");
		return "util/msg";
	} // 회원가입
	
	@GetMapping("/login")
	public String login(Model model) {
		return "/member/login";
	}
	
	@PostMapping("/update/{mb_num}")
	public String update(Model model, MemberVO user) {
		boolean res = memberService.updateMember(user);
		model.addAttribute("msg", res ? "회원 정보 수정에 성공하셨습니다." : "회원 정보 수정에 실패하였습니다.");
		model.addAttribute("url", res ? "/admin/member/list" : "/admin/member/update/" + user.getMb_num());
		return "util/msg";
	} // (관리자) 회원 정보 수정
	
	@PostMapping("/password/{mb_num}")
	public String password(RedirectAttributes redirect, MemberVO user) {
		boolean res = memberService.updatePw(user);
		redirect.addFlashAttribute("msg", res ? "비밀번호 변경에 성공하셨습니다." : "비밀번호 변경에 실패하셨습니다.");
		return "redirect:/admin/member/update/" + user.getMb_num();
	}
}