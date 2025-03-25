package kr.spring.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.spring.boot.model.vo.CommunityVO;
import kr.spring.boot.model.vo.PostVO;
import kr.spring.boot.pagination.Criteria;

public interface CommunityDAO {

	List<CommunityVO> selectCommnunityList();

	List<PostVO> selectPostList(@Param("co_num")int co_num);

	int selectPostCount(@Param("co_num")int co_num, @Param("cri")Criteria cri);
}
