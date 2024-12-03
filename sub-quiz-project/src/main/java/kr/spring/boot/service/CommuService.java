package kr.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.boot.dao.CommuDAO;
import kr.spring.boot.model.vo.CommunityVO;

@Service
public class CommuService {

	@Autowired
	private CommuDAO commuDAO;
	
	public List<CommunityVO> getAllcoummu() {
		return commuDAO.CommuSelect();
	}

}
