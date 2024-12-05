package kr.spring.boot.model.util;

import java.security.SecureRandom;

import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Configuration
public class CustomUtil {

    //렌덤한 숫자 반환
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
}