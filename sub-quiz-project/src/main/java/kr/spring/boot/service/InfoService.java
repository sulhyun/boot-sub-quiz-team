package kr.spring.boot.service;

import java.util.Map;

public interface InfoService {

	boolean updateInfo(String mb_id, Map<String, String> params);

}
