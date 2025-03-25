package kr.spring.boot.service;

import java.util.List;

import kr.spring.boot.model.vo.CommunityVO;
import kr.spring.boot.model.vo.PostVO;
import kr.spring.boot.pagination.PageMaker;
import kr.spring.boot.pagination.PostCriteria;

public interface CommunityService {

	List<CommunityVO> getCommunityList();

	List<PostVO> getPostList(PostCriteria cri);
	
	PageMaker getPageMaker(PostCriteria cri);

}
