package com.ezen.project.service;

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.ezen.project.Repository.FamilyRepository;
import com.ezen.project.Repository.MemberRepository;

import com.ezen.project.model.Family;
import com.ezen.project.model.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WalkTLoginService {

	
	@Autowired
	private FamilyRepository signUpRepository;
	
	@Autowired
	private MemberRepository memberRepository;
		
	@Autowired
	private JavaMailSender sender;
	public boolean sendMineMessage(String delEmail, String familyPwd)
	   {
		//System.out.println(delEmail+":이메일 입장 delEmail :"+familyPwd);
		
	      MimeMessage mimeMessage = sender.createMimeMessage();
	      String content ="<h3>인증 확인 입니다.</h3>"
	        		+ "<form method='post' action='http://localhost:58172/pet_login/form_complate?delEmail="+delEmail+"&familyPwd="+familyPwd+"'>"
	        		+ "<input type='submit' value='인증 확인'>"
	        		+ "</form>";
	      String controll = "text/html;charset=utf-8";
	      try {
	         InternetAddress[] addressTo = new InternetAddress[1];
	         addressTo[0] = new InternetAddress(delEmail); //이메일
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
	
	public Member memberSignUpForm(Member member,Long FamilyId) { 
		Member fa = memberRepository.save(member);
		memberRepository.update_value(FamilyId, member.getMemberId());
		return fa;
	}
	
	public Long findIdByMemberEmail(String email) {
		return memberRepository.findByMemberEmail(email).get(0).getMemberId();
	}
	
	public String findNameByMemberEmail(String email) {
		return memberRepository.findByMemberEmail(email).get(0).getMemberName();
	}
	
	public Long findByDelEmailId(String delEmail) {
		return signUpRepository.findByDelEmail(delEmail).getFamilyId();
	}
	public String findByDelEmail(String delEmail) {
		return signUpRepository.findByDelEmail(delEmail).getDelEmail();
	}
	
	public String findByDelMemberEmail(String memberEmail) {
		return memberRepository.findByMemberEmail(memberEmail).get(0).getMemberEmail();
	}
	public String findByDelMemberPwd(String memberEmail) {
		return memberRepository.findByMemberEmail(memberEmail).get(0).getMemberPw();
	}
	
	public List<Member> getList() {	
		List<Member> resEntity = memberRepository.findAll();
		return resEntity;
	}
	
}