package kr.spring.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.spring.boot.model.vo.QuizChoiceVO;
import kr.spring.boot.model.vo.QuizSubjectiveVO;
import kr.spring.boot.model.vo.QuizTypeVO;

public interface AdminDAO {

	List<QuizTypeVO> selectQuizCategories();
	
	int insertCategory(QuizTypeVO quizType);
	
	int deleteQuizCategory(int qt_num);

	QuizTypeVO selectQuizCategoryById(int qtNum);

	int updateQuizCategory(QuizTypeVO quizType);

	List<QuizChoiceVO> getChoiceQuizListByCategory(@Param("qtNum")int qtNum);

	List<QuizSubjectiveVO> getSubjectiveQuizListByCategory(@Param("qtNum")int qtNum);

	List<QuizTypeVO> selectQuizType();

	boolean insertQuizType(@Param("qt_name")String qt_name);

	boolean deleteQuizType(@Param("qt_num")int qt_num);

	boolean updateQuizType(@Param("qt_num")int qt_num, @Param("qt_name")String qt_name);
}
