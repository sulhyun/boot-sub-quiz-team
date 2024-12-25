package kr.spring.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.spring.boot.model.vo.QuizTypeVO;
import kr.spring.boot.pagination.Criteria;

public interface AdminDAO {

	List<QuizTypeVO> selectQuizType();

	boolean insertQuizType(@Param("qt_name")String qt_name);

	boolean deleteQuizType(@Param("qt_num")int qt_num);

	boolean updateQuizType(@Param("qt_num")int qt_num, @Param("qt_name")String qt_name);

	int getCount(@Param("cri")Criteria cri, @Param("qt_num")int qt_num);

	List<?> selectQuizListByChoice(@Param("cri")Criteria cri, @Param("qt_num")int qt_num);

	List<?> selectQuizListBySubjective(@Param("cri")Criteria cri, @Param("qt_num")int qt_num);

}
