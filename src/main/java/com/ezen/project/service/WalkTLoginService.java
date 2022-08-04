package com.ezen.project.service;

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ezen.project.Repository.MemberRepository;
import com.ezen.project.Repository.SignUpRepository;
import com.ezen.project.model.Family;
import com.ezen.project.model.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WalkTLoginService {

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private SignUpRepository signUpRepository;
	
	@Autowired
	private MemberRepository memberRepository;
		
	public boolean sendMineMessage(String delEmail, String familyPwd)
	   {
	      MimeMessage mimeMessage = sender.createMimeMessage();
	      String content ="<h3>인증 확인 입니다.</h3>"
	        		+ "<form method='post' action='http://localhost:58172/pet_login/form_complate?delEmail="+delEmail+"&familyPwd="+familyPwd+"'>"
	        		+ "<input type='submit' value='인증 확인'>"
	        		+ "</form>";
	      String controll = "text/html;charset=utf-8";
	      try {
	         InternetAddress[] addressTo = new InternetAddress[1];
	         addressTo[0] = new InternetAddress(delEmail);
	         mimeMessage.setRecipients(Message.RecipientType.TO, addressTo);
	         mimeMessage.setSubject("이메일 인증 입니다.");  //제목
	         mimeMessage.setContent(content, controll);  //내용        
	         sender.send(mimeMessage);         
	         
	         return true;
	      } catch (MessagingException e) {
	         log.error("에러={}", e);
	      }
	      return false;
	   }
	
	public Family familySignUpForm(String delEmail, String family_pwd) {
        Family family = new Family();
		family.setDelEmail(delEmail);
		family.setFamilyPwd(family_pwd);
		Family fa = signUpRepository.save(family);
		return fa;
	}
	
	public Member memberSignUpForm(Member member) {  
		Member fa = memberRepository.save(member);
		return fa;
	}
	
	public Long findByDelEmailId(String delEmail) {
		return signUpRepository.findByDelEmail(delEmail).getFamilyId();
	}
	public String findByDelEmail(String delEmail) {
		return signUpRepository.findByDelEmail(delEmail).getDelEmail();
	}
	public Family findByDelAll(String delEmail) {
		return signUpRepository.findByDelEmail(delEmail);
	}
}
