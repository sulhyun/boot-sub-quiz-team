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
		return "/admin/adminHome";		
	}
	
	@GetMapping("/quiz/type")
	public String quizType(Model model, Principal principal) {
        List<QuizTypeVO> list = adminService.getQuizType();
        model.addAttribute("list", list);
        return "/admin/quiz/type";
	} // 퀴즈 카테고리 조회
	
    @PostMapping("/quiz/type/add")
    public String quizTypeAdd(@RequestParam String qt_name, RedirectAttributes redirectAttributes) {
        boolean res = adminService.addQuizType(qt_name);
        redirectAttributes.addFlashAttribute("msg", res ? "카테고리 추가를 성공했습니다." : "카테고리 추가를 실패했습니다.");
        return "redirect:/admin/quiz/type";
    } // 퀴즈 카테고리 추가
    
    @PostMapping("/quiz/type/del")
    public String quizTypeDel(@RequestParam int qt_num, RedirectAttributes redirectAttributes) {
	    boolean res = adminService.delQuizType(qt_num);
	    redirectAttributes.addFlashAttribute("msg", res ? "카테고리 삭제를 성공했습니다." : "카테고리 삭제를 실패했습니다.");
	    return "redirect:/admin/quiz/type";
	} // 퀴즈 카테고리 삭제
    
    @PostMapping("/quiz/type/update")
    public String quizTypeUpdate(@RequestParam int qt_num, @RequestParam String qt_name, RedirectAttributes redirectAttributes) {
	    boolean res = adminService.updateQuizType(qt_num, qt_name);
	    redirectAttributes.addFlashAttribute("msg", res ? "카테고리 수정을 성공했습니다." : "카테고리 수정을 실패했습니다.");
	    return "redirect:/admin/quiz/type";
    } // 퀴즈 카테고리 수정
    
    @GetMapping("/quiz/detail/{qt_num}/{type}")
    public String quizDetail(Model model, Criteria cri, @PathVariable int qt_num, @PathVariable String type) {
    	String qt_name = adminService.getQuizTypeName(qt_num);
    	cri.setPerPageNum(8);
    	PageMaker pm = adminService.getPageMaker(cri, qt_num);
    	List<?> list = adminService.getQuizList(cri, qt_num);
    	model.addAttribute("qt_name", qt_name);
    	model.addAttribute("list", list);
    	model.addAttribute("pm", pm);
        return "/admin/quiz/detail";
    } // 퀴즈 조회(페이지네이션)
    
    @GetMapping("/quiz/insert/{qt_num}/{type}")
    public String quizInsert(Model model, @PathVariable int qt_num, @PathVariable String type) {
    	String qt_name = adminService.getQuizTypeName(qt_num);
    	System.out.println(qt_num + "," + type);
    	model.addAttribute("qt_name", qt_name);
        return "/admin/quiz/insert";
    } // 퀴즈 등록
}
