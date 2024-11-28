package kr.spring.boot.service;

import org.springframework.stereotype.Service;

import kr.spring.boot.dao.MailDAO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailServiceImp implements MailService {

	private MailDAO mailDao;
	
	@Override
	public boolean setMailCode(String evc_email, String code) {
		if(evc_email == null) {
			return false;
		}
		return mailDao.insertMailCode(evc_email, code);
	}

}
