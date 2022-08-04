package com.ezen.project.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.project.Repository.SignUpRepository;
import com.ezen.project.model.Family;
import com.ezen.project.model.Member;
import com.ezen.project.service.WalkTLoginService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/pet_login")
public class WalkTLoginController {
		
		private int cerNum;
		private Random rendom;	

		@Autowired
		private WalkTLoginService svc;
				
		
		@Autowired
		private HttpSession session;
	
		//familySignUpForm x 로그인 화면 화면 
		@GetMapping("/family_sign_up_form")
		public String familySignInForm()
		{
			       return "thymeleaf/login/familySignUpForm";
		}
		//familyLoginForm o 로그인 화면 화면 
		@GetMapping("/family_login_form")
		public String familyLoginForm()
		{
				   return "thymeleaf/login/familyLoginForm";
		}
		
		//familySignUpForm POST 형식으로 보낸다.
		@PostMapping("/family_form")
		public String mailCertificationForm(@RequestParam("delEmail")String delEmail,
											@RequestParam("familyPwd")String familyPwd,
											HttpSession session, Model model) {
			
			svc.sendMineMessage(delEmail,familyPwd);	
			return "thymeleaf/login/mailCertificationForm";	
		}
		//회원 가입 화면 
		@PostMapping("/form_complate")
		public String familySignUpForm(@RequestParam("delEmail")String delEmail,
				@RequestParam("familyPwd")String familyPwd
				,Model model){
			
				//검증 아이디 값이 중복일경우
			
			try {
					Family familyEmail = svc.findByDelAll(delEmail);
					session.setAttribute("email_id", familyEmail);
					return "thymeleaf/login/memberSignUpForm";
			} catch (Exception e) {
				svc.familySignUpForm(delEmail, familyPwd);
				return "thymeleaf/login/memberSignUpForm";
			}

		}	
		@PostMapping("/member_form")
		public String memberForm(Member member,Model model) {
			System.out.println(member.getMemberAge());
			System.out.println(member.getMemberEmail());
			System.out.println(member.getMemberName());
			System.out.println(member.getMemberPhoneNumber());
			System.out.println(member.getMemberPw());
			
			System.out.println("get:"+session.getAttribute("email_id"));
			Family family = (Family) session.getAttribute("email_id");
			member.setFamily(family);
			System.out.println("세션 : "+member.getFamily());
			
			
			member.setMemberAge((Integer)(member.getMemberAge()));
			svc.memberSignUpForm(member);	
			return "thymeleaf/mainIndex";	
		}
		
		//아이디 찾기 화면 
		@GetMapping("/lost_id_find_form")
		public String lostIdFindIdForm()
		{
			       return "thymeleaf/login/lostIdFindIdForm";
		}	
		//비밀번호 찾기 화면 
		@GetMapping("/lost_pw_fInd_form")
		public String lostPwFIndPwForm()
		{
			       return "thymeleaf/login/lostPwFIndPwForm";
		}
		
		//아이디 찾기 메일 검증 화면 
		@GetMapping("/login_find_id_mail")
		public String loginFindIdMail()
		{
			       return "thymeleaf/login/loginForm";
		}	
		//비밀번호 찾기 메일 검증 화면 
		@GetMapping("/login_find_pw_mail")
		public String loginFindPwMail()
		{
			       return "thymeleaf/login/signUpForm";
		}	
		
}
