package kr.spring.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.boot.model.vo.EventVO;

@Mapper
public interface EventDAO {


	void evtaddpage(EventVO event);

	List<EventVO> selectEvent();

}
