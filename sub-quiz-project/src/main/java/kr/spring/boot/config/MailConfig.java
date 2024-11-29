package kr.spring.boot.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587); // 구글은 587 사용

        mailSender.setUsername("sulhyun4788@gmail.com"); 
        mailSender.setPassword("khlf kgmd nbvf cbca"); //앱비밀번호는 직접 입력해서 쓰기  

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); //보안설정
        props.put("mail.smtp.starttls.required", "true"); // 보안설정
        props.put("mail.debug", "false");  // 디버깅을 위해 true 설정
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 보안설정

        return mailSender;
    }
}
