package kr.spring.boot.service;

import java.util.List;

import kr.spring.boot.model.vo.QuizChoiceVO;
import kr.spring.boot.model.vo.QuizTypeVO;
import kr.spring.boot.pagination.Criteria;
import kr.spring.boot.pagination.PageMaker;

public interface AdminService {

	List<QuizTypeVO> getQuizType();

	boolean addQuizType(String qt_name);

	boolean delQuizType(int qt_num);

	boolean updateQuizType(int qt_num, String qt_name);

	PageMaker getPageMaker(Criteria cri, int qt_num);

	List<?> getQuizList(Criteria cri, int qt_num);

	String getQuizTypeName(int qt_num);

	boolean addQuiz(QuizChoiceVO quiz);

}
