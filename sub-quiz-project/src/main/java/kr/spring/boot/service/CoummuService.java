package kr.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.boot.dao.CoummuDAO;
import kr.spring.boot.model.vo.CommunityVO;

@Service
public class CoummuService {

	@Autowired
	private CoummuDAO coummuDAO;
	
	public List<CommunityVO> getAllcoummu() {
		return coummuDAO.CoummuSelect();
	}

}
