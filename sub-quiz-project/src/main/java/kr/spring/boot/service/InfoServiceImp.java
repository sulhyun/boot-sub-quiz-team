package kr.spring.boot.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.spring.boot.dao.InfoDAO;
import kr.spring.boot.dao.MemberDAO;
import kr.spring.boot.model.vo.MemberVO;
import kr.spring.boot.model.vo.PointVO;
import kr.spring.boot.pagination.Criteria;
import kr.spring.boot.pagination.PageMaker;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InfoServiceImp implements InfoService {
	
	private BCryptPasswordEncoder passwordEncoder;
	private InfoDAO infoDao;
	private MemberDAO memberDao;

	@Override
	public boolean updateInfo(String mb_id, Map<String, String> params) {
		switch(params.get("type")) {
		case "name":
			if(!checkNull(params.get("mb_name"))) {
				return false;
			}
			return infoDao.updateInfo(mb_id, params);
		case "nick":
			if(!checkNull(params.get("mb_nick"))) {
				return false;
			}
			return infoDao.updateInfo(mb_id, params);
		case "contact":
			if(!checkNull(params.get("mb_hp")) || !checkRegex(params.get("mb_hp"), "^010\\d{4}\\d{4}$")) {
				return false;
			}
			return infoDao.updateInfo(mb_id, params);
		case "address":
			if(!checkNull(params.get("mb_zip")) || !checkNull(params.get("mb_addr")) || !checkNull(params.get("mb_addr2"))) {
				return false;
			}
			return infoDao.updateInfo(mb_id, params);
		}
		return false;
	}
	
	@Override
	public boolean updatePw(String mb_id, Map<String, String> params) {
		MemberVO user = memberDao.selectMember(mb_id);
		if(!checkNull(params.get("mb_pw")) || !checkRegex(params.get("mb_pw"), "^[a-zA-Z0-9!@#$]{6,20}$")) {
			return false;
		}
		if(!passwordEncoder.matches(params.get("mb_pw"), user.getMb_pw())) {
			return false;
		}
		params.replace("mb_pw2", passwordEncoder.encode(params.get("mb_pw2")));
		return infoDao.updatePw(mb_id, params);
	}
	
	@Override
	public boolean cancelMember(String mb_id, Map<String, String> params) {
		MemberVO user = memberDao.selectMember(mb_id);
		if(!mb_id.equals(params.get("id"))) {
			return false;
		}
		if(!passwordEncoder.matches(params.get("pw"), user.getMb_pw())) {
			return false;
		}
		return infoDao.cancelMember(mb_id, params);
	}
	
	private boolean checkNull(String str) {
		if(str == null || str.trim().length() == 0) {
			return false;
		}
		return true;
	}
	
	private boolean checkRegex(String str, String regex) {
		if (str != null && Pattern.matches(regex, str)) {
			return true;
		}
		return false;
	}
	
	@Override
	public PageMaker getPageMaker(Criteria cri, String mb_id) {
		int count = infoDao.getCount(cri, mb_id);
		return new PageMaker(5, cri, count);
	}

	@Override
	public List<PointVO> getPointList(Criteria cri, String mb_id) {
		if(mb_id == null) {
			return null;
		}
		return infoDao.selectPointList(cri, mb_id);
	}

}
