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

import kr.spring.boot.model.vo.QuizTypeVO;
import kr.spring.boot.pagination.Criteria;
import kr.spring.boot.pagination.PageMaker;
import kr.spring.boot.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
	} // 퀴즈 카테고리 조회
	
    @PostMapping("/quiz/type/add")
    public String addQuizCategory(@RequestParam String qt_name) {
        boolean res = adminService.addQuizType(qt_name);
        log.info("insert quiz_type : {}", res);
        return "redirect:/admin/quiz/type";
    } // 퀴즈 카테고리 추가
    
    @PostMapping("/quiz/type/del")
    public String deleteQuizCategory(@RequestParam int qt_num) {
	    boolean res = adminService.delQuizType(qt_num);
	    log.info("delete quiz_type : {}", res);
	    return "redirect:/admin/quiz/type";
	} // 퀴즈 카테고리 삭제
    
    @PostMapping("/quiz/type/update")
    public String updateQuizCategory(@RequestParam int qt_num, @RequestParam String qt_name) {
	    boolean res = adminService.updateQuizType(qt_num, qt_name);
	    log.info("delete quiz_type : {}", res);
	    return "redirect:/admin/quiz/type";
    } // 퀴즈 카테고리 수정
    
    @GetMapping("/quiz/detail/{qt_num}/{type}")
    public String quizList(Model model, Criteria cri, @PathVariable int qt_num, @PathVariable String type) {
    	cri.setPerPageNum(8);
    	PageMaker pm = adminService.getPageMaker(cri, qt_num);
    	List<?> list = adminService.getQuizList(cri, qt_num);
    	model.addAttribute("list", list);
    	model.addAttribute("pm", pm);
        return "/admin/quiz/detail";
    } // 퀴즈 조회(페이지네이션)
    
    @GetMapping("/quiz/insert/{qt_num}/{type}")
    public String createChoiceQuiz(Model model, @PathVariable int qt_num, @PathVariable String type) {
    	System.out.println(qt_num + "," + type);
        return "/admin/quiz/insert";
    }
}
