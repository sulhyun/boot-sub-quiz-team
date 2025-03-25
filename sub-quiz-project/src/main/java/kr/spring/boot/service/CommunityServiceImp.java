package kr.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.boot.dao.CommunityDAO;
import kr.spring.boot.model.vo.CommunityVO;
import kr.spring.boot.model.vo.PostVO;
import kr.spring.boot.pagination.PageMaker;
import kr.spring.boot.pagination.PostCriteria;

@Service
public class CommunityServiceImp implements CommunityService {

	@Autowired
	private CommunityDAO communityDao;
	
	@Override
	public List<CommunityVO> getCommunityList() {
		return communityDao.selectCommnunityList();
	}

	@Override
	public List<PostVO> getPostList(PostCriteria cri) {
		return communityDao.selectPostList(cri.getCo_num());
	}

	@Override
	public PageMaker getPageMaker(PostCriteria cri) {
		if(cri == null) {
			return null;
		}
		int totalCount = communityDao.selectPostCount(cri);
		return new PageMaker(3, cri, totalCount);
	}

}
