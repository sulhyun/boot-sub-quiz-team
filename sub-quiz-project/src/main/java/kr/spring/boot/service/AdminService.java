package kr.spring.boot.service;

import java.util.List;

import kr.spring.boot.model.vo.QuizTypeVO;

public interface AdminService {

	List<QuizTypeVO> getQuizType();

	boolean addQuizType(String qt_name);

	boolean delQuizType(int qt_num);

	boolean updateQuizType(int qt_num, String qt_name);

}
