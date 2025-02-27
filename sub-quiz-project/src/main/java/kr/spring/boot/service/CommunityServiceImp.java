package kr.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.boot.dao.CommunityDAO;
import kr.spring.boot.model.vo.CommunityVO;
import kr.spring.boot.model.vo.PostVO;

@Service
public class CommunityServiceImp implements CommunityService {

	@Autowired
	private CommunityDAO communityDao;
	
	@Override
	public List<CommunityVO> getCommunityList() {
		return communityDao.selectCommnunityList();
	}

	@Override
	public List<PostVO> getPostList(int co_num) {
		return communityDao.selectPostList(co_num);
	}

}
