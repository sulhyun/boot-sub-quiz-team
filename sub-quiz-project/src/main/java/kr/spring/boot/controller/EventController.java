package kr.spring.boot.controller;

<<<<<<< Updated upstream
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.boot.model.vo.EventVO;
import kr.spring.boot.service.EventService;
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

>>>>>>> Stashed changes
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/event")
public class EventController {
<<<<<<< Updated upstream
	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/eventpage")
	public String event(Model model) {
		
		return null;		
	}
	
	@GetMapping("/addevent")
	public String addevent(Model model) {
		return null;
	}
	
	@PostMapping("/addevent/eventaddpage")
	public String addpage(EventVO event, Model model) {
		System.out.println(event);
		
		boolean res = eventService.eventaddpage(event); 
		
        if (res == false) {
        	model.addAttribute("msg", "실패");
			model.addAttribute("url", "/event/eventpage");
			return "util/msg";
        }        
        return "redirect:/event/eventpage";
	}

=======

	@GetMapping("/eventpage")
	public String event(Model model){
		return null;
	}
	
>>>>>>> Stashed changes
}
