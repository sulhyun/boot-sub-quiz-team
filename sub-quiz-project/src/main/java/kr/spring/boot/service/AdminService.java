package kr.spring.boot.service;

import java.util.List;

import kr.spring.boot.model.vo.MemberVO;
import kr.spring.boot.model.vo.QuizChoiceVO;
import kr.spring.boot.model.vo.QuizSubjectiveVO;
import kr.spring.boot.model.vo.QuizTypeVO;
import kr.spring.boot.pagination.Criteria;
import kr.spring.boot.pagination.PageMaker;

public interface AdminService {

	List<QuizTypeVO> getQuizType();

	boolean addQuizType(String qt_name);

	boolean delQuizType(int qt_num);

	boolean updateQuizType(int qt_num, String qt_name);

	PageMaker getPageMakerByQuiz(Criteria cri, int qt_num);

	List<?> getQuizList(Criteria cri, int qt_num);

	String getQuizTypeName(int qt_num);

	boolean addQuizChoice(QuizChoiceVO quiz);

	boolean addQuizSubjective(QuizSubjectiveVO quiz);

	boolean delQuizChoice(QuizChoiceVO quiz);

	boolean delQuizSubjective(QuizSubjectiveVO quiz);

	QuizChoiceVO getQuizChoice(int qu_num);

	QuizSubjectiveVO getQuizSubjective(int qs_num);

	boolean updateQuizChoice(QuizChoiceVO quiz);

	boolean updateQuizSubjective(QuizSubjectiveVO quiz);

	List<MemberVO> getMemberList(Criteria cri);

	PageMaker getPageMakerByMember(Criteria cri);
	
	MemberVO getMember(int mb_num);

	boolean delMember(MemberVO user);

	List<MemberVO> getPointList(Criteria cri);

	PageMaker getPageMakerByPoint(Criteria cri);

}
