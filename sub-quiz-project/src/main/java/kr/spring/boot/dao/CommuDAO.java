package kr.spring.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.spring.boot.model.vo.CommunityVO;
import kr.spring.boot.model.vo.PostVO;

public interface CommuDAO {


	List<CommunityVO> commuSelect();

	List<PostVO> postSelect(@Param("co_num") int co_num);
}
