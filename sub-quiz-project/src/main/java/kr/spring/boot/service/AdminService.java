package kr.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.boot.dao.AdminDAO;
import kr.spring.boot.model.vo.QuizChoiceVO;
import kr.spring.boot.model.vo.QuizSubjectiveVO;
import kr.spring.boot.model.vo.QuizTypeVO;


@Service
public class AdminService {
	
	@Autowired
	private AdminDAO adminDao;
	
	public List<QuizTypeVO> getQuizCategories() {
		
		return adminDao.selectQuizCategories();
	}
    public boolean addCategory(String categoryName) {
        if (categoryName != null && !categoryName.trim().isEmpty()) {
            QuizTypeVO quizType = new QuizTypeVO();
            quizType.setQt_name(categoryName);
            return adminDao.insertCategory(quizType) > 0; // 성공 여부 확인
        }
        return false;
    }

    public boolean deleteQuizCategory(int qtNum) {
        return adminDao.deleteQuizCategory(qtNum) > 0; // 성공 여부 확인
    }

    public QuizTypeVO getQuizCategoryById(int qtNum) {
        return adminDao.selectQuizCategoryById(qtNum);
    }

    public boolean updateQuizCategory(int qtNum, String categoryName) {
        if (categoryName != null && !categoryName.trim().isEmpty()) {
            QuizTypeVO quizType = new QuizTypeVO();
            quizType.setQt_num(qtNum);
            quizType.setQt_name(categoryName);
            return adminDao.updateQuizCategory(quizType) > 0; // 성공 여부 확인
        }
        return false;
    }
	public List<QuizChoiceVO> getChoiceQuizListByCategory(int qtNum) {
		
		return adminDao.getChoiceQuizListByCategory(qtNum);
	}
	public List<QuizSubjectiveVO> getSubjectiveQuizListByCategory(int qtNum) {
		
		return adminDao.getSubjectiveQuizListByCategory(qtNum);
	}
}
