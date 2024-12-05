package kr.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.boot.dao.EventDAO;
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
	

	
}
