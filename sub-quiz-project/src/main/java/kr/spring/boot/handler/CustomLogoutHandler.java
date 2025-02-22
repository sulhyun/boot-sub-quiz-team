package kr.spring.boot.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.spring.boot.model.util.OAuth2CustomUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof OAuth2CustomUser) {
            OAuth2CustomUser customUser = (OAuth2CustomUser) authentication.getPrincipal();
            String provider = customUser.getMember().getMb_login_method();
            String accessToken = customUser.getAccessToken(); 

            if ("kakao".equals(provider)) {
            	logoutKakao(accessToken);
            } else if ("naver".equals(provider)) {
            	logoutNaver(accessToken);
            }
        }
    }
    
    private void logoutKakao(String accessToken) {
        String logoutUrl = "https://kapi.kakao.com/v1/user/unlink";
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);

            HttpEntity<Void> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.postForEntity(logoutUrl, request, String.class);

            log.info("카카오 연결 끊기 응답: {}", response.getBody());
        } catch (Exception e) {
            log.error("카카오 연결 끊기 실패", e);
        }
    }

    private void logoutNaver(String accessToken) {
        String logoutUrl = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id={client_id}&client_secret={client_secret}&access_token={access_token}&service_provider=NAVER";
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> params = new HashMap<>();
            params.put("client_id", "네이버 클라이언트 ID");
            params.put("client_secret", "네이버 클라이언트 Secret");
            params.put("access_token", accessToken);

            ResponseEntity<String> apiResponse = restTemplate.getForEntity(logoutUrl, String.class, params);
            log.info("네이버 토큰 삭제 응답: {}", apiResponse.getBody());

        } catch (Exception e) {
            log.error("네이버 토큰 삭제 실패", e);
        }
    }
}
