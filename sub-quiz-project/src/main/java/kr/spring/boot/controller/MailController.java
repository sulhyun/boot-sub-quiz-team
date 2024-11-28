package kr.spring.boot.controller;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.internet.MimeMessage;
import kr.spring.boot.model.util.CustomUtil;
import kr.spring.boot.service.MailService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/mail")
public class MailController {

	private CustomUtil customUtil;
	private JavaMailSender mailSender;
	private MailService mailService;
	
	@PostMapping("/setCode")
	public boolean setCode(@RequestParam String evc_email) {
		String code = customUtil.getCustomNumber(6);
		System.out.println(code);
		String mail = "안녕하세요. 퀴즈사이트 회원 인증 메일입니다. 6자리 코드를 회원가입 창에서 입력해주세요. <p>인증코드 : " + code + "</p>";
		boolean res = mailService.setMailCode(evc_email, code);
		if(res) {
			mailSend(evc_email, "퀴즈 인증 이메일", mail);
		}
		return res;
	}

	private boolean mailSend(String to, String title, String content) {
	    String from = "quiz@gmail.com";
	    try{
	    	MimeMessage msg = mailSender.createMimeMessage();
	        MimeMessageHelper mh = new MimeMessageHelper(msg, true, "UTF-8");
	
	        mh.setFrom(from);			// 보내는사람 (생략 불가능)
	        mh.setTo(to);				// 받는사람 이메일
	        mh.setSubject(title);		// 메일제목 (생략 가능)
	        mh.setText(content, true);	// 메일 내용
	
	        mailSender.send(msg);
	        return true;
	    } catch(Exception e){
            e.printStackTrace();
            return false;
        }
	}
}
