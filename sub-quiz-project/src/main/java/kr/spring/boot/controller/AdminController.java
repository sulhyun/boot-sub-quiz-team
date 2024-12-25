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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.boot.model.vo.QuizChoiceVO;
import kr.spring.boot.model.vo.QuizSubjectiveVO;
import kr.spring.boot.model.vo.QuizTypeVO;
import kr.spring.boot.service.AdminService;
import kr.spring.boot.service.AdminServiceImp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/adminhome")
	public String Admin(Model model) {
		return "/admin/adminHome";		
	}
	
	@GetMapping("/quiz/type")
	public String adminquizcategory(Model model, Principal principal) {
        List<QuizTypeVO> list = adminService.getQuizType();
        model.addAttribute("list", list);
        return "/admin/quiz/type";
	}
	
    @PostMapping("/quiz/type/add")
    public String addQuizCategory(@RequestParam String qt_name) {
        boolean res = adminService.addQuizType(qt_name);
        log.info("insert quiz_type : {}", res);
        return "redirect:/admin/quiz/type";
    }
    
    @PostMapping("/quiz/type/del")
    public String deleteQuizCategory(@RequestParam int qt_num) {
	    boolean res = adminService.delQuizType(qt_num);
	    log.info("delete quiz_type : {}", res);
	    return "redirect:/admin/quiz/type";
	}
    
    @PostMapping("/quiz/type/update")
    public String updateQuizCategory(@RequestParam int qt_num, @RequestParam String qt_name) {
	    boolean res = adminService.updateQuizType(qt_num, qt_name);
	    log.info("delete quiz_type : {}", res);
	    return "redirect:/admin/quiz/type";
    }
    
    @GetMapping("/quizlist/{qt_num}")
    public String quizList(@PathVariable("qt_num") int qtNum, Model model) {
    	
    	List<QuizChoiceVO> ChoiceQuizList = adminService.getChoiceQuizListByCategory(qtNum);
    	
    	List<QuizSubjectiveVO> SubjectiveQuizList = adminService.getSubjectiveQuizListByCategory(qtNum);
        model.addAttribute("ChoiceQuizList", ChoiceQuizList);
        model.addAttribute("SubjectiveQuizList",SubjectiveQuizList);
        model.addAttribute("qt_num", qtNum);
        
        return "/admin/quizlist";
    }
    @GetMapping("/createchoicequiz")
    public String createChoiceQuiz(@RequestParam("qt_num") int qtNum, Model model) {
        model.addAttribute("qt_num", qtNum);
        return "/admin/createchoicequiz";
    }

    @GetMapping("/createsubjectivequiz")
    public String createSubjectiveQuiz(@RequestParam("qt_num") int qtNum, Model model) {
        model.addAttribute("qt_num", qtNum);
        return "/admin/createsubjectivequiz";
    }
}
