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

import kr.spring.boot.model.vo.MemberVO;
import kr.spring.boot.model.vo.QuizChoiceVO;
import kr.spring.boot.model.vo.QuizSubjectiveVO;
import kr.spring.boot.model.vo.QuizTypeVO;
import kr.spring.boot.pagination.Criteria;
import kr.spring.boot.pagination.PageMaker;
import kr.spring.boot.service.AdminService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/adminhome")
	public String admin(Model model) {
		return "admin/adminHome";		
	}
	
	@GetMapping("/quiz/type")
	public String quizType(Model model, Principal principal) {
        List<QuizTypeVO> list = adminService.getQuizType();
        model.addAttribute("list", list);
        return "admin/quiz/type";
	} // 퀴즈 카테고리 조회
	
    @PostMapping("/quiz/type/add")
    public String quizTypeAdd(RedirectAttributes redirect, @RequestParam String qt_name) {
        boolean res = adminService.addQuizType(qt_name);
        redirect.addFlashAttribute("msg", res ? "카테고리 추가에 성공하였습니다." : "카테고리 추가에 실패하였습니다.");
        return "redirect:/admin/quiz/type";
    } // 퀴즈 카테고리 추가
    
    @PostMapping("/quiz/type/del")
    public String quizTypeDel(RedirectAttributes redirect, @RequestParam int qt_num) {
	    boolean res = adminService.delQuizType(qt_num);
	    redirect.addFlashAttribute("msg", res ? "카테고리 삭제에 성공하였습니다." : "카테고리 삭제에 실패하였습니다.");
	    return "redirect:/admin/quiz/type";
	} // 퀴즈 카테고리 삭제
    
    @PostMapping("/quiz/type/update")
    public String quizTypeUpdate(RedirectAttributes redirect, @RequestParam int qt_num, @RequestParam String qt_name) {
	    boolean res = adminService.updateQuizType(qt_num, qt_name);
	    redirect.addFlashAttribute("msg", res ? "카테고리 수정에 성공하였습니다." : "카테고리 수정에 실패하였습니다.");
	    return "redirect:/admin/quiz/type";
    } // 퀴즈 카테고리 수정
    
    @GetMapping("/quiz/detail/{qt_num}/{type}")
    public String quizDetail(Model model, Criteria cri, @PathVariable int qt_num, @PathVariable String type) {
    	String qt_name = adminService.getQuizTypeName(qt_num);
    	cri.setPerPageNum(8);
    	PageMaker pm = adminService.getPageMakerByQuiz(cri, qt_num);
    	List<?> list = adminService.getQuizList(cri, qt_num);
    	model.addAttribute("qt_name", qt_name);
    	model.addAttribute("list", list);
    	model.addAttribute("pm", pm);
        return "admin/quiz/detail";
    } // 퀴즈 조회(페이지네이션)
    
    @GetMapping("/quiz/insert/{qt_num}/{type}")
    public String quizInsert(Model model, @PathVariable int qt_num, @PathVariable String type) {
    	String qt_name = adminService.getQuizTypeName(qt_num);
    	model.addAttribute("qt_name", qt_name);
        return "admin/quiz/insert";
    } // 퀴즈 등록 화면
    
    @PostMapping("/quiz/insert/{qt_num}/choice")
    public String quizAddChoice(Model model, QuizChoiceVO quiz) {
    	boolean res = adminService.addQuizChoice(quiz);
    	model.addAttribute("msg", res ? "퀴즈 등록에 성공하였습니다." : "퀴즈 등록에 실패하였습니다.");
    	model.addAttribute("url", res ? "/admin/quiz/detail/" + quiz.getQt_num() + "/choice" : "/admin/quiz/insert/" + quiz.getQt_num() + "/choice");
    	return "util/msg";
    } // 객관식 퀴즈 등록
    
    @PostMapping("/quiz/insert/{qt_num}/subjective")
    public String quizAddSubjective(Model model, QuizSubjectiveVO quiz) {
    	boolean res = adminService.addQuizSubjective(quiz);
    	model.addAttribute("msg", res ? "퀴즈 등록에 성공하였습니다." : "퀴즈 등록에 실패하였습니다.");
    	model.addAttribute("url", res ? "/admin/quiz/detail/" + quiz.getQt_num() + "/subjective" : "/admin/quiz/insert/" + quiz.getQt_num() + "/subjective");
    	return "util/msg";
    } // 주관식 퀴즈 등록
    
    @PostMapping("/quiz/del/{qt_num}/{qu_num}/choice")
    public String quizDelChoice(RedirectAttributes redirectAttributes, QuizChoiceVO quiz) {
    	boolean res = adminService.delQuizChoice(quiz);
	    redirectAttributes.addFlashAttribute("msg", res ? "퀴즈 삭제에 성공하였습니다." : "퀴즈 삭제에 실패하였습니다.");
	    return "redirect:/admin/quiz/detail/" + quiz.getQt_num() + "/choice";
    } // 객관식 퀴즈 삭제
    
    @PostMapping("/quiz/del/{qt_num}/{qs_num}/subjective")
    public String quizDelSubjective(RedirectAttributes redirectAttributes, QuizSubjectiveVO quiz) {
    	boolean res = adminService.delQuizSubjective(quiz);
	    redirectAttributes.addFlashAttribute("msg", res ? "퀴즈 삭제에 성공하였습니다." : "퀴즈 삭제에 실패하였습니다.");
	    return "redirect:/admin/quiz/detail/" + quiz.getQt_num() + "/subjective";
    } // 객관식 퀴즈 삭제
    
    @GetMapping("/quiz/update/{qt_num}/{num}/{type}")
    public String quizUpdate(Model model, @PathVariable int qt_num, @PathVariable int num, @PathVariable String type) {
    	String qt_name = adminService.getQuizTypeName(qt_num);
    	QuizChoiceVO choice = null;
    	QuizSubjectiveVO subjective = null;
    	switch(type) {
    	case "choice":
    		choice = adminService.getQuizChoice(num);
    		break;
    	case "subjective":
    		subjective = adminService.getQuizSubjective(num);
    	}
    	model.addAttribute("quiz", choice == null ? subjective : choice);
    	model.addAttribute("qt_name", qt_name);
        return "admin/quiz/update";
    } // 퀴즈 수정 화면
    
    @PostMapping("/quiz/update/{qt_num}/{qu_num}/choice")
    public String quizUpdateChoice(Model model, QuizChoiceVO quiz) {
    	boolean res = adminService.updateQuizChoice(quiz);
    	model.addAttribute("msg", res ? "퀴즈 수정에 성공하였습니다." : "퀴즈 수정에 실패하였습니다.");
    	model.addAttribute("url", "/admin/quiz/detail/" + quiz.getQt_num() + "/choice");
    	return "util/msg";
    } // 객관식 퀴즈 수정
    
    @PostMapping("/quiz/update/{qt_num}/{qs_num}/subjective")
    public String quizUpdateSubjective(Model model, QuizSubjectiveVO quiz) {
    	boolean res = adminService.updateQuizSubjective(quiz);
    	model.addAttribute("msg", res ? "퀴즈 수정에 성공하였습니다." : "퀴즈 수정에 실패하였습니다.");
    	model.addAttribute("url", "/admin/quiz/detail/" + quiz.getQt_num() + "/subjective");
    	return "util/msg";
    } // 주관식 퀴즈 수정
    
    @GetMapping("/member/list")
    public String memberList(Model model, Criteria cri) {
    	cri.setPerPageNum(8);
    	//List<MemberVO> list = adminService.getMemberList();
        return "admin/member/list";
    }
    
}
