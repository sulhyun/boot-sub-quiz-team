package kr.spring.boot.service;

import org.springframework.stereotype.Service;

import kr.spring.boot.dao.MailDAO;
import kr.spring.boot.model.vo.EmailVerificationVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailServiceImp implements MailService {

	private MailDAO mailDao;
	
	@Override
	public boolean setMailCode(String mb_email, String code) {
		if(mb_email == null) {
			return false;
		}
		return mailDao.insertMailCode(mb_email, code);
	}

	@Override
	public boolean getMailCode(String mb_email, String code) {
		if(mb_email == null) {
			return false;
		}
		EmailVerificationVO evc = mailDao.selectMailCode(mb_email);
		if(evc.getEvc_code().equals(code)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delMailCode(String mb_email) {
		if(mb_email == null) {
			return false;
		}
		return mailDao.deleteMailCode(mb_email);
	}

}
