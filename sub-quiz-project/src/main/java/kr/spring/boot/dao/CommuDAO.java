package kr.spring.boot.dao;

import java.util.List;

import kr.spring.boot.model.vo.CommunityVO;

public interface CommuDAO {


	List<CommunityVO> commuSelect();

	List<CommunityVO> detailSelect();
}
