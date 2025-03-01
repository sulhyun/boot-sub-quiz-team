package kr.spring.boot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.spring.boot.model.vo.InquiryVO;
import kr.spring.boot.model.vo.PointVO;
import kr.spring.boot.pagination.Criteria;

public interface InfoDAO {
	
	boolean updateInfo(@Param("mb_id")String mb_id, @Param("params")Map<String, String> params);
	
	boolean updatePw(@Param("mb_id")String mb_id, @Param("params")Map<String, String> params);
	
	boolean cancelMember(@Param("mb_id")String mb_id, @Param("params")Map<String, String> params);

	int getCountByPoint(@Param("cri")Criteria cri, @Param("mb_id")String mb_id);

	List<PointVO> selectPointList(@Param("cri")Criteria cri, @Param("mb_id")String mb_id);

	int getCountByInquiry(@Param("cri")Criteria cri, @Param("mb_id")String mb_id);

	List<PointVO> selectInquiryList(@Param("cri")Criteria cri, @Param("mb_id")String mb_id);

	boolean insertInquiry(@Param("inquiry")InquiryVO inquiry);

	InquiryVO selectInquiry(@Param("iq_num")int iq_num);

}
