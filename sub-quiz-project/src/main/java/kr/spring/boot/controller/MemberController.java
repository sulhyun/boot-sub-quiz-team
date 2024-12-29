package kr.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
}