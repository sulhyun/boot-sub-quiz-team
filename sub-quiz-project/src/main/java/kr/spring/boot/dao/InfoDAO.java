package kr.spring.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.spring.boot.model.vo.PointVO;
import kr.spring.boot.pagination.Criteria;

public interface InfoDAO {

	boolean updateInfo(@Param("type")String type, @Param("mb_id")String mb_id, @Param("mb_attr")String mb_attr);

	int getCount(@Param("cri")Criteria cri, @Param("mb_id")String mb_id);

	List<PointVO> selectPointList(@Param("cri")Criteria cri, @Param("mb_id")String mb_id);

}
