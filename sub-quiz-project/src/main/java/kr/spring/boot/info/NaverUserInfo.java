package kr.spring.boot.info;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NaverUserInfo implements OAuth2UserInfo {

	private Map<String, Object> attributes;
	
    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
    	return (String) ((Map) attributes.get("response")).get("id");
    }

    @Override
    public String getEmail() {
        return (String) ((Map) attributes.get("response")).get("email");
    }

    @Override
    public String getName() {
        return (String) ((Map) attributes.get("response")).get("name");
    }

}
