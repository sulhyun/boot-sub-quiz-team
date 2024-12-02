package kr.spring.boot.controller;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@ResponseBody
	@PostMapping("/setCode")
	public boolean setCode(@RequestParam String mb_email) {
		String code = customUtil.getCustomNumber(6);
		String mail = "안녕하세요.<br>퀴즈Q 회원 인증 메일입니다.<br>6자리 코드를 회원가입 창에서 입력해주세요. <p>인증코드 : <strong>" + code + "</strong></p>";
		boolean res = mailService.setMailCode(mb_email, code);
		if(res) {
			mailSend(mb_email, "퀴즈Q 인증 이메일", mail);
		}
		return res;
	} // 인증 코드를 실제 이메일 주소에 전송하는 역할 + DB 저장

	@ResponseBody
	@PostMapping("/getCode")
	public boolean getCode(@RequestParam String mb_email, @RequestParam String code) {
		return mailService.getMailCode(mb_email, code);
	}
	
	@ResponseBody
	@PostMapping("/delCode")
	public boolean delCode(@RequestParam String mb_email) {
		return mailService.delMailCode(mb_email);
	}
	
	private boolean mailSend(String to, String title, String content) {
	    String from = "quizQ@gmail.com";
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
