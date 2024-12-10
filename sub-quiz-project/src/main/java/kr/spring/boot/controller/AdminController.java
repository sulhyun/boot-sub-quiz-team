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
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/adminhome")
	public String Admin(Model model) {
		
		return "/admin/adminHome";		
	}
	@GetMapping("/adminquizcategory")
	public String adminquizcategory(Model model,Principal principal) {

        List<QuizTypeVO> quizCategories = adminService.getQuizCategories();
        
        if (quizCategories == null || quizCategories.isEmpty()) {
            model.addAttribute("error", "카테고리 목록을 불러올 수 없습니다.");
        }
        
        model.addAttribute("quizCategories", quizCategories);

        return "/admin/adminquizcategory";
	}
    @PostMapping("/addquizcategory")
    public String addQuizCategory(@RequestParam("categoryName") String categoryName, RedirectAttributes redirectAttributes) {
        boolean Add = adminService.addCategory(categoryName);
        
        
        if (Add) {
            redirectAttributes.addFlashAttribute("message", "카테고리가 성공적으로 추가되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("message", "카테고리 추가에 실패했습니다.");
        }

        return "redirect:/admin/adminquizcategory";
    }
    @PostMapping("/adminquizcategory/delete")
    public String deleteQuizCategory(
    	    @RequestParam("qt_num") int qtNum,
    	    RedirectAttributes redirectAttributes) {
    	    
    	    System.out.println("삭제할 카테고리 ID: " + qtNum);
    	    boolean deleted = adminService.deleteQuizCategory(qtNum);

    	    redirectAttributes.addFlashAttribute("message", deleted ? "삭제 성공" : "삭제 실패");
    	    return "redirect:/admin/adminquizcategory";
    	}
    @PostMapping("/adminquizcategory/update")
    public String updateQuizCategory(
    	    @RequestParam("qt_num") int qtNum,
    	    @RequestParam("categoryName") String categoryName,
    	    RedirectAttributes redirectAttributes) {
    	    boolean updated = adminService.updateQuizCategory(qtNum, categoryName);
    	   
    	    redirectAttributes.addFlashAttribute("message", updated ? "수정 성공" : "수정 실패");
    	    return "redirect:/admin/adminquizcategory";
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
