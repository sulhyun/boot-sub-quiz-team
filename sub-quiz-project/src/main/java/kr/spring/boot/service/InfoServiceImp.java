package kr.spring.boot.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import kr.spring.boot.dao.InfoDAO;
import kr.spring.boot.model.vo.PointVO;
import kr.spring.boot.pagination.Criteria;
import kr.spring.boot.pagination.PageMaker;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InfoServiceImp implements InfoService {
	
	private InfoDAO infoDao;

	@Override
	public boolean updateInfo(String mb_id, Map<String, String> params) {
		switch(params.get("type")) {
		case "name":
			if(params.get("mb_name") == null || params.get("mb_name").trim().length() == 0) {
				return false;
			}
			return infoDao.updateInfo(mb_id, params);
		case "nick":
			if(params.get("mb_nick") == null || params.get("mb_nick").trim().length() == 0) {
				return false;
			}
			return infoDao.updateInfo(mb_id, params);
		case "contact":
			if(params.get("mb_hp") == null || params.get("mb_hp").trim().length() == 0 || !checkRegex(params.get("mb_hp"), "^010\\d{4}\\d{4}$")) {
				return false;
			}
			return infoDao.updateInfo(mb_id, params);
		case "address":
			if(params.get("mb_zip") == null || params.get("mb_zip").trim().length() == 0 || params.get("mb_addr") == null || params.get("mb_addr").trim().length() == 0 || params.get("mb_addr2") == null || params.get("mb_addr2").trim().length() == 0) {
				return false;
			}
			return infoDao.updateInfo(mb_id, params);
		}
		return false;
	} // 회원 정보 수정
	
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
