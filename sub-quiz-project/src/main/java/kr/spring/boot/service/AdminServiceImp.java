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
		try {
			if(qt_name == null || qt_name.trim().length() == 0) {
				return false;
			}
		    return adminDao.insertQuizType(qt_name);
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean delQuizType(int qt_num) {
		try {
			return adminDao.deleteQuizType(qt_num);
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean updateQuizType(int qt_num, String qt_name) {
		try {
			return adminDao.updateQuizType(qt_num, qt_name);
		} catch (Exception e) {
			return false;
		}
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

	@Override
	public String getQuizTypeName(int qt_num) {
		return adminDao.selectQuizTypeName(qt_num);
	}

	@Override
	public boolean addQuizChoice(QuizChoiceVO quiz) {
		try {
			if(quiz == null) {
				return false;
			}
			if(quiz.getQu_content() == null || quiz.getQu_content().trim().length() == 0) {
				return false;
			}
			if(quiz.getQu_answer1() == null || quiz.getQu_answer1().trim().length() == 0) {
				return false;
			}
			if(quiz.getQu_answer2() == null || quiz.getQu_answer2().trim().length() == 0) {
				return false;
			}
			if(quiz.getQu_answer3() == null || quiz.getQu_answer3().trim().length() == 0) {
				return false;
			}
			if(quiz.getQu_answer4() == null || quiz.getQu_answer4().trim().length() == 0) {
				return false;
			}
			return adminDao.insertQuizChoice(quiz);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addQuizSubjective(QuizSubjectiveVO quiz) {
		try {
			if(quiz == null) {
				return false;
			}
			if(quiz.getQs_content() == null || quiz.getQs_content().trim().length() == 0) {
				return false;
			}
			if(quiz.getQs_correct_answer() == null || quiz.getQs_correct_answer().trim().length() == 0) {
				return false;
			}
			return adminDao.insertQuizSubjective(quiz);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delQuizChoice(QuizChoiceVO quiz) {
		try {
			if(quiz == null) {
				return false;
			}
			return adminDao.deleteQuizChoice(quiz);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delQuizSubjective(QuizSubjectiveVO quiz) {
		try {
			if(quiz == null) {
				return false;
			}
			return adminDao.deleteQuizSubjective(quiz);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public QuizChoiceVO getQuizChoice(int qu_num) {
		return adminDao.selectQuizChoice(qu_num);
	}

	@Override
	public QuizSubjectiveVO getQuizSubjective(int qs_num) {
		return adminDao.selectQuizSubjective(qs_num);
	}

	@Override
	public boolean updateQuizChoice(QuizChoiceVO quiz) {
		try {
			if(quiz == null) {
				return false;
			}
			if(quiz.getQu_content() == null || quiz.getQu_content().trim().length() == 0) {
				return false;
			}
			if(quiz.getQu_answer1() == null || quiz.getQu_answer1().trim().length() == 0) {
				return false;
			}
			if(quiz.getQu_answer2() == null || quiz.getQu_answer2().trim().length() == 0) {
				return false;
			}
			if(quiz.getQu_answer3() == null || quiz.getQu_answer3().trim().length() == 0) {
				return false;
			}
			if(quiz.getQu_answer4() == null || quiz.getQu_answer4().trim().length() == 0) {
				return false;
			}
			return adminDao.updateQuizChoice(quiz);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateQuizSubjective(QuizSubjectiveVO quiz) {
		try {
			if(quiz == null) {
				return false;
			}
			if(quiz.getQs_content() == null || quiz.getQs_content().trim().length() == 0) {
				return false;
			}
			if(quiz.getQs_correct_answer() == null || quiz.getQs_correct_answer().trim().length() == 0) {
				return false;
			}
			return adminDao.updateQuizSubjective(quiz);
		} catch (Exception e) {
			return false;
		}
	}

}
