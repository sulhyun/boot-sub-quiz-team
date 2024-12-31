package kr.spring.boot.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.boot.dao.EventDAO;
import kr.spring.boot.model.vo.EventPrizeVO;
import kr.spring.boot.model.vo.EventVO;

@Service
public class EventService {

	@Autowired
	private EventDAO eventDAO;
	
	public boolean eventaddpage(EventVO event) {
		eventDAO.evtaddpage(event);
		return true;
	}

	public List<EventVO> getevent() {
		return eventDAO.selectEvent();
	}

	public EventVO eventDetail(int ev_num) {
		return eventDAO.selectEventDetail(ev_num);
	}

	public List<EventVO> geteventEnd() {
		return eventDAO.selectEventEnd();
	}

	public boolean evtPrizeAdd(EventPrizeVO evtprizeVO) {
		eventDAO.evtPrizeAdd(evtprizeVO);
		return true;
	}

	public EventPrizeVO eventPrize(int ev_num) {
		return eventDAO.selectEventPrize(ev_num);
	}

	 public boolean insertEvent(int ev_num) {
        int result = eventDAO.insertEvent(ev_num);
        return result > 0;
    }

	public void updateExpiredEvents() {
		LocalDate today = LocalDate.now();
		// 이벤트 참여자에서
		List<EventVO> eventVO = eventDAO.selectEventEnd();
		for(EventVO event : eventVO) {
			int eventNum = event.getEv_num();
			
			boolean hasWinners = eventDAO.checkEventHasWinners(eventNum);
			if (!hasWinners) {
                System.out.println("이벤트 ID " + eventNum + "에 당첨자가 없어! 없으니까 그걸해!");
                
            }
		}
	}
	
}
