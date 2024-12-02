package kr.spring.boot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.spring.boot.model.dto.LoginDTO;
import kr.spring.boot.model.dto.SignupDTO;
import kr.spring.boot.model.vo.MemberVO;
import kr.spring.boot.service.MemberService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/member")
public class MemberController {

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
		if(res) {
			model.addAttribute("msg", "회원가입 성공!!");
			model.addAttribute("url", "/");
		}else {
			model.addAttribute("msg", "회원가입 실패!!");
			model.addAttribute("url", "/");
		}
		return "util/msg";
	} // 회원가입
	
	@GetMapping("/login")
	public String login() {
		return "/member/login";
	}
	
	@PostMapping("/login")
	public String loginPost(Model model, HttpServletRequest request, LoginDTO user) {
		MemberVO member = memberService.login(user);
		if(member == null) {
			model.addAttribute("msg", "로그인 실패!!");
			model.addAttribute("url", "/member/login");
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("user", member);
			model.addAttribute("msg", "로그인 성공!!");
			model.addAttribute("url", "/");
		}
		return "util/msg";
	} // MemberDetailService에서 처리하기 때문에 필요없음..

}
