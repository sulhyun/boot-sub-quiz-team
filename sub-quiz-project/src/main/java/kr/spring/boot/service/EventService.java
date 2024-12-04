package kr.spring.boot.service;

import org.springframework.stereotype.Service;

import kr.spring.boot.dao.EventDAO;
import kr.spring.boot.model.vo.EventVO;

@Service
public class EventService {

	private EventDAO eventDAO;
	
	public boolean eventaddpage(EventVO event) {
		eventDAO.evtaddpage(event);
		return true;
	}
	
	
	
}
