package kr.spring.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.spring.boot.model.vo.CommunityVO;
import kr.spring.boot.model.vo.FileVO;
import kr.spring.boot.model.vo.PostVO;
import kr.spring.boot.pagination.PostCriteria;

public interface CommunityDAO {

	List<CommunityVO> selectCommnunityList();

	List<PostVO> selectPostList(@Param("cri")PostCriteria cri);

	int selectPostCount(@Param("cri")PostCriteria cri);

	String selectCommunityName(@Param("co_num")int co_num);

	boolean insertPost(@Param("post")PostVO post);

	void insertFile(@Param("file")FileVO file);

}
