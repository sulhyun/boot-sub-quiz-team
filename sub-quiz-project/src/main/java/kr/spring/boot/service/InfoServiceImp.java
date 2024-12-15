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
		String type = params.get("type");
		String mb_name = params.get("mb_name");
		String mb_nick = params.get("mb_nick");
		String mb_hp = params.get("mb_hp");
		switch(type) {
		case "name":
			if(mb_name == null || mb_name.trim().length() == 0) {
				return false;
			}
			return infoDao.updateInfo(type, mb_id, mb_name);
		case "nick":
			if(mb_nick == null || mb_nick.trim().length() == 0) {
				return false;
			}
			return infoDao.updateInfo(type, mb_id, mb_nick);
		case "contact":
			if(mb_hp == null || mb_hp.trim().length() == 0 || !checkRegex(mb_hp, "^010\\d{4}\\d{4}$")) {
				return false;
			}
			return infoDao.updateInfo(type, mb_id, mb_hp);
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
