package kr.spring.boot.service;

import java.util.List;

import kr.spring.boot.model.vo.CommunityVO;
import kr.spring.boot.model.vo.PostVO;

public interface CommunityService {

	List<CommunityVO> getCommunityList();

	List<PostVO> getPostList(int co_num);

}
