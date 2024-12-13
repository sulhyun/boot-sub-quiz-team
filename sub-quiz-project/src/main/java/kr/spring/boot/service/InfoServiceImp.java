package kr.spring.boot.service;

import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import kr.spring.boot.dao.InfoDAO;
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

}
