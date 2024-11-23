package kr.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
/*
 * 퀴즈 수정,등록,삭제
 * 힌트 수정,등록,삭제
 * 퀴즈 카테골르 등록 수정 삭제
 * 사용자 문의 내용 리스트
 * 문의에 대한 답변처리 기능(추후 문의가 만들어졌을때 제작)
 * 회원관리 등록 수정 삭제
 * 포인트 관리
 * 홈페이지 기본 사항 관리
 * */
@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/adminHome")
	public String Admin(Model model) {
		
		return null;		
	}

}
