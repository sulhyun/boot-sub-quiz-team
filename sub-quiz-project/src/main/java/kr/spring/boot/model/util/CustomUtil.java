package kr.spring.boot.model.util;

import java.security.SecureRandom;

import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Configuration
public class CustomUtil {

    // 랜덤한 숫자 반환
    public String getCustomNumber(int len){
        SecureRandom sr = new SecureRandom();
        int up = (int)Math.pow(10, len);
        int num = sr.nextInt(up);
        String code = String.valueOf(num);
        while(code.length() < len) {
        	code = "0" + code;
        }
        return code;
    }
    
    // 전화번호 하이픈 생성
    public String autoHyphen(String hp) {
    	if(hp == null || hp.length() != 11) {
    		return hp;
    	}
    	return hp.substring(0, 3) + "-" + hp.substring(3, 4) + "***-" + hp.substring(7, 8) + "****";
    }
}
