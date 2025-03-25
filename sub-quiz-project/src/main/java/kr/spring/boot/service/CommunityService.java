package kr.spring.boot.service;

import java.util.List;

import kr.spring.boot.model.vo.CommunityVO;
import kr.spring.boot.model.vo.PostVO;
import kr.spring.boot.pagination.Criteria;
import kr.spring.boot.pagination.PageMaker;

public interface CommunityService {

	List<CommunityVO> getCommunityList();

	List<PostVO> getPostList(int co_num, Criteria cri);
	
	PageMaker getPageMaker(int co_num, Criteria cri);

}
