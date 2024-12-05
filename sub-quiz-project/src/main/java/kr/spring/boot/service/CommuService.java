package kr.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.boot.dao.CommuDAO;
import kr.spring.boot.model.vo.CommunityVO;
import kr.spring.boot.model.vo.PostVO;

@Service
public class CommuService {

	@Autowired
	private CommuDAO commuDao;
	
	public List<CommunityVO> getAllcoummu() {
		return commuDao.commuSelect();
	}

	public List<PostVO> getPostList(int co_num) {
		return commuDao.postSelect(co_num);
	}
}
