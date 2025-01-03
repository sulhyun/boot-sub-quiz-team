package kr.spring.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.spring.boot.model.vo.MemberVO;
import kr.spring.boot.model.vo.PointVO;
import kr.spring.boot.model.vo.QuizChoiceVO;
import kr.spring.boot.model.vo.QuizSubjectiveVO;
import kr.spring.boot.model.vo.QuizTypeVO;
import kr.spring.boot.pagination.Criteria;

public interface AdminDAO {

	List<QuizTypeVO> selectQuizType();

	boolean insertQuizType(@Param("qt_name")String qt_name);

	boolean deleteQuizType(@Param("qt_num")int qt_num);

	boolean updateQuizType(@Param("qt_num")int qt_num, @Param("qt_name")String qt_name);

	int getQuizCount(@Param("cri")Criteria cri, @Param("qt_num")int qt_num);

	List<?> selectQuizListByChoice(@Param("cri")Criteria cri, @Param("qt_num")int qt_num);

	List<?> selectQuizListBySubjective(@Param("cri")Criteria cri, @Param("qt_num")int qt_num);

	String selectQuizTypeName(@Param("qt_num")int qt_num);

	boolean insertQuizChoice(@Param("quiz")QuizChoiceVO quiz);

	boolean insertQuizSubjective(@Param("quiz")QuizSubjectiveVO quiz);

	boolean deleteQuizChoice(@Param("quiz")QuizChoiceVO quiz);

	boolean deleteQuizSubjective(@Param("quiz")QuizSubjectiveVO quiz);

	QuizChoiceVO selectQuizChoice(@Param("qu_num")int qu_num);

	QuizSubjectiveVO selectQuizSubjective(@Param("qs_num")int qs_num);

	boolean updateQuizChoice(@Param("quiz")QuizChoiceVO quiz);

	boolean updateQuizSubjective(@Param("quiz")QuizSubjectiveVO quiz);

	List<MemberVO> selectMemberList(@Param("cri")Criteria cri);

	int selectMemberCount(@Param("cri")Criteria cri);

	MemberVO selectMember(@Param("mb_num")int mb_num);

	boolean deleteMember(@Param("user")MemberVO user);

	List<MemberVO> selectPointList(@Param("cri")Criteria cri);

	int selectPointCount(@Param("cri")Criteria cri);

	MemberVO selectMemberById(@Param("mb_id")String mb_id);

	boolean updateMemberPoint(@Param("point")PointVO point);

	boolean insertPoint(@Param("point")PointVO point);

}
