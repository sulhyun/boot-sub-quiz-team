package kr.spring.boot.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.boot.model.dto.EventListDTO;
import kr.spring.boot.model.vo.EventPrizeVO;
import kr.spring.boot.model.vo.EventVO;
import kr.spring.boot.service.EventService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	// 이벤트 페이지
	// 이벤트 페이지 리스트 넘기기
	@GetMapping("/eventpage")
	public String event(Model model) {
		List<EventVO> eventVO = eventService.getevent();
		model.addAttribute("list", eventVO);
		return "/event/eventpage";		
	}
	
	// 이벤트 종료 페이지
	// 현제 시간 이후 종료된 이벤트 출력
	@GetMapping("/eventpageEnd")
	public String eventEnd(Model model) {
		List<EventVO> eventVO = eventService.geteventEnd();
		model.addAttribute("list", eventVO);
		return "/event/eventpageEnd";		
	}
	
	// 이벤트 당첨 페이지
	// 아직 미구현
	@GetMapping("/eventpageWinner")
	public String eventWinner(Model model) {
		List<EventVO> eventVO = eventService.getevent();
		model.addAttribute("list", eventVO);
		return "/event/eventpageWinner";		
	}
	
	// 이벤트 등록 페이지 이동
	@GetMapping("/addevent")
	public String addevent(Model model) {
		return "/event/addevent";
	}
	
	// 이벤트 등록 페이지
	// 정규식 적용해야함
	@PostMapping("/addevent/eventaddpage")
	public String addpage(EventVO event, Model model) {
		boolean res = eventService.eventaddpage(event); 
        if (res == false) {
        	model.addAttribute("msg", "실패");
			model.addAttribute("url", "/event/eventpage");
			return "util/msg";
        }        
        return "redirect:/event/eventpage";
	}
	
	// 이벤트 상세 페이지
	@GetMapping("/eventdetail/{ev_num}")
	public String eventDetail(Model model, @PathVariable int ev_num) {
		EventPrizeVO evtprizeVO = eventService.eventPrize(ev_num);
		EventVO eventVO = eventService.eventDetail(ev_num);
		model.addAttribute("event", eventVO);
		model.addAttribute("eventprize", evtprizeVO);
		
		return "/event/eventdetail";
	}
	
	// 이벤트 상품 등록 페이지 이동
	@GetMapping("/eventprize")
	public String eventPrize(Model model) {
		List<EventVO> eventVO = eventService.getevent();
		model.addAttribute("event", eventVO);
		return "/event/eventprize";
	}
	
	// 이벤트 경품 등록
	@PostMapping("/eventprize/prize")
	public String eventPrizeAdd(EventPrizeVO evtprizeVO, Model model) {
		boolean res = eventService.evtPrizeAdd(evtprizeVO);
		if (res == false) {
        	model.addAttribute("msg", "실패");
			model.addAttribute("url", "/event/eventpage");
			return "util/msg";
        }
		return "redirect:/event/eventpage";
	}
	
    @ResponseBody
    @PostMapping("/eventdetail/eventapply")
    public Map<String, Object> applyEvent(@RequestBody EventListDTO request) {
        Map<String, Object> response = new HashMap<>();
        try {
            int evNum = request.getEv_num();
            boolean isInserted = eventService.insertEvent(evNum);
            if (isInserted) {
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("message", "응모 처리 중 문제가 발생했습니다.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "서버 오류: " + e.getMessage());
        }
        return response;
    }
    
}
