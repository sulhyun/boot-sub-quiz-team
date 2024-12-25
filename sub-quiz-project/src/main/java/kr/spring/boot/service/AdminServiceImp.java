package kr.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.boot.dao.AdminDAO;
import kr.spring.boot.model.vo.QuizChoiceVO;
import kr.spring.boot.model.vo.QuizSubjectiveVO;
import kr.spring.boot.model.vo.QuizTypeVO;
import kr.spring.boot.pagination.Criteria;
import kr.spring.boot.pagination.PageMaker;


@Service
public class AdminServiceImp implements AdminService {
	
	@Autowired
	private AdminDAO adminDao;
	
	@Override
	public List<QuizTypeVO> getQuizType() {
		return adminDao.selectQuizType();
	}
	
	@Override
	public boolean addQuizType(String qt_name) {
		if(qt_name == null || qt_name.trim().length() == 0) {
			return false;
		}
		return adminDao.insertQuizType(qt_name);
	}
	
	@Override
	public boolean delQuizType(int qt_num) {
		return adminDao.deleteQuizType(qt_num);
	}
	
	@Override
	public boolean updateQuizType(int qt_num, String qt_name) {
		return adminDao.updateQuizType(qt_num, qt_name);
	}

	@Override
	public PageMaker getPageMaker(Criteria cri, int qt_num) {
		int count = adminDao.getCount(cri, qt_num);
		return new PageMaker(5, cri, count);
	}

	@Override
	public List<?> getQuizList(Criteria cri, int qt_num) {
		switch(cri.getType()) {
		case "choice":
			return adminDao.selectQuizListByChoice(cri, qt_num);
		case "subjective":
			return adminDao.selectQuizListBySubjective(cri, qt_num);
		}
		return null;
	}

}
