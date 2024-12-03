package kr.spring.boot.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUserInfoDTO {
	
    @JsonProperty("id")
    private Long id; 				// 카카오 회원 번호
    @JsonProperty("connected_at")
    private Date connectedAt; 		// 연결 완료 시각
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;
    
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KakaoAccount {
    	
        @JsonProperty("profile")
        private Profile profile; 		// 프로필 정보
        @JsonProperty("name")
        private String name; 			// 이름
        @JsonProperty("email")
        private String email; 			// 이메일
        @JsonProperty("phone_number")
        private String phoneNumber; 	// 전화번호
        @JsonProperty("birthyear")
        private String birthYear;		 // 출생 연도
        @JsonProperty("birthday")
        private String birthDay; 		// 생일 (MMDD)
        @JsonProperty("gender")
        private String gender; 			// 성별 (male, female)

        @Data
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Profile {
        	
            @JsonProperty("nickname")
            private String nickName; 				// 닉네임
            @JsonProperty("thumbnail_image_url")
            private String thumbnailImageUrl; 		// 프로필 미리보기 이미지 URL
            @JsonProperty("profile_image_url")
            public String profileImageUrl;			// 프로필 이미지 URL
        }
    }
}
