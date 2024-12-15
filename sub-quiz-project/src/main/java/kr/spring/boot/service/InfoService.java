package kr.spring.boot.service;

import java.util.List;
import java.util.Map;

import kr.spring.boot.model.vo.PointVO;
import kr.spring.boot.pagination.Criteria;
import kr.spring.boot.pagination.PageMaker;

public interface InfoService {

	boolean updateInfo(String mb_id, Map<String, String> params);

	PageMaker getPageMaker(Criteria cri, String mb_id);

	List<PointVO> getPointList(Criteria cri, String mb_id);

}
