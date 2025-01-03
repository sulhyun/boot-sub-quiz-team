package kr.spring.boot.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.boot.model.util.CustomUtil;
import kr.spring.boot.model.vo.MemberVO;
import kr.spring.boot.model.vo.PointVO;
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
	
	private AdminService adminService;
	private CustomUtil customUtil;
	
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
    public String quizDelChoice(RedirectAttributes redirect, QuizChoiceVO quiz) {
    	boolean res = adminService.delQuizChoice(quiz);
    	redirect.addFlashAttribute("msg", res ? "퀴즈 삭제에 성공하였습니다." : "퀴즈 삭제에 실패하였습니다.");
	    return "redirect:/admin/quiz/detail/" + quiz.getQt_num() + "/choice";
    } // 객관식 퀴즈 삭제
    
    @PostMapping("/quiz/del/{qt_num}/{qs_num}/subjective")
    public String quizDelSubjective(RedirectAttributes redirect, QuizSubjectiveVO quiz) {
    	boolean res = adminService.delQuizSubjective(quiz);
    	redirect.addFlashAttribute("msg", res ? "퀴즈 삭제에 성공하였습니다." : "퀴즈 삭제에 실패하였습니다.");
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
    	List<MemberVO> list = adminService.getMemberList(cri);
    	for(MemberVO user : list) {
    		user.setMb_hp(customUtil.autoHyphen(user.getMb_hp()));
    	}
    	PageMaker pm = adminService.getPageMakerByMember(cri);
    	model.addAttribute("list", list);
		model.addAttribute("pm", pm);
        return "admin/member/list";
    } // 회원 관리 화면(페이지 네이션)
    
    @GetMapping("/member/insert")
    public String memberInsert() {
    	return "admin/member/insert";
    } // 회원 등록 화면
    
    @PostMapping("/member/del/{mb_num}")
    public String memberDel(RedirectAttributes redirect, @PathVariable int mb_num) {
    	MemberVO user = adminService.getMember(mb_num);
    	boolean res = adminService.delMember(user);
    	if(user.getMb_out_date() == null) {
    		redirect.addFlashAttribute("msg", res ? "회원 정지에 성공하였습니다." : "회원 정지에 실패하였습니다.");
    	} else {
    		redirect.addFlashAttribute("msg", res ? "회원 정지 해제에 성공하였습니다." : "회원 정지 해제에 실패하였습니다.");
    	}
    	return "redirect:/admin/member/list";
    } // 회원 정지
    
    @GetMapping("/member/update/{mb_num}")
    public String memberUpdate(Model model, @PathVariable int mb_num) {
    	MemberVO user = adminService.getMember(mb_num);
    	user.setMb_hp(customUtil.autoHyphen(user.getMb_hp()));
    	model.addAttribute("user", user);
    	return "admin/member/update";
    } // 회원 수정
    
    @GetMapping("/point/list")
    public String pointList(Model model, Criteria cri) {
    	cri.setPerPageNum(8);
    	List<MemberVO> list = adminService.getPointList(cri);
    	PageMaker pm = adminService.getPageMakerByPoint(cri);
    	model.addAttribute("list", list);
		model.addAttribute("pm", pm);
    	return "admin/point/list";
    } // 포인트 관리 화면(페이지 네이션)
    
    @GetMapping("/point/insert")
    public String pointInsert() {
    	return "admin/point/insert";
    } // 포인트 등록 화면
    
    @PostMapping("/point/insert")
    public String pointInsertPost(Model model, PointVO point) {
    	boolean res = adminService.addPoint(point);
    	model.addAttribute("msg", res ? "포인트 등록에 성공하셨습니다." : "포인트 등록에 실패하였습니다.");
    	model.addAttribute("url", "/admin/point/list");
    	return "util/msg";
    } // 포인트 등록
    
    @GetMapping("/point/update/{pi_num}")
    public String pointUpdate(Model model, @PathVariable int pi_num) {
    	PointVO point = adminService.getPoint(pi_num);
    	model.addAttribute("point", point);
    	return "admin/point/update";
    } // 포인트 수정 화면
    
    @PostMapping("/point/update/{pi_num}")
    public String pointUpdatePost(Model model, PointVO point) {
    	boolean res = adminService.updatePoint(point);
    	model.addAttribute("msg", res ? "포인트 수정에 성공하셨습니다." : "포인트 수정에 실패하였습니다.");
    	model.addAttribute("url", "/admin/point/list");
    	return "util/msg";
    } // 포인트 수정
    
    @PostMapping("/point/del/{pi_num}")
    public String pointDel(RedirectAttributes redirect, @PathVariable int pi_num) {
    	PointVO point = adminService.getPoint(pi_num);
    	boolean res = adminService.delPoint(point);
    	redirect.addFlashAttribute("msg", res ? "포인트 삭제에 성공하셨습니다." : "포인트 삭제에 실패했습니다.");
    	return "redirect:/admin/point/list";
    }
}
