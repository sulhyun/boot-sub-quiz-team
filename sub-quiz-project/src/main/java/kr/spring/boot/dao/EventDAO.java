package kr.spring.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.boot.model.vo.EventPrizeVO;
import kr.spring.boot.model.vo.EventVO;

@Mapper
public interface EventDAO {


	void evtaddpage(EventVO event);

	List<EventVO> selectEvent();

	EventVO selectEventDetail(int ev_num);

	List<EventVO> selectEventEnd();

	void evtPrizeAdd(EventPrizeVO evtprizeVO);

	EventPrizeVO selectEventPrize(int ev_num);

	int insertEvent(int ev_num);

	boolean checkEventHasWinners(int eventNum);

}
