package kr.spring.boot.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.boot.model.util.CustomUtil;
import kr.spring.boot.model.vo.MemberVO;
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
		user.setMb_hp(customUtil.autoHyphen(user.getMb_hp()));
		model.addAttribute("user", user);
		return "info/profile";
	}
	
	@PostMapping("/profile")
	public String basicPost(Model model, Principal principal, @RequestParam Map<String, String> params) {
		String mb_id = principal.getName();
		boolean res = infoService.updateInfo(mb_id, params);
		model.addAttribute("msg", res ? "수정 성공!!" : "수정 실패!!");
		model.addAttribute("url", "/info/profile");
		return "util/msg";
	} // 회원 정보 수정
	
	@GetMapping("/point/{type}")
	public String point(Model model, Principal principal, @PathVariable String type) {
		return "info/point";
	}
}
