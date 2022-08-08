package com.ezen.project.controller;

import java.util.HashMap;
import java.util.Iterator;
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

import com.ezen.project.model.Family;
import com.ezen.project.model.Member;
import com.ezen.project.service.WalkTLoginService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/pet_login")
public class WalkTLoginController {
		

		@Autowired
		private WalkTLoginService svc;
				
		
		@Autowired
		private HttpSession session;
	 
		@GetMapping("/introduceindex")
	    public String introduceIndex(Model model)
	    {	
	       return "thymeleaf/introduce/index";
	    }
		
		@GetMapping("/member_sign_in_form")
		public String MemberSignInForm()
		{
			       return "thymeleaf/login/MemberLoginForm";
		}
		
		@PostMapping("/member_sign_in_detail")
		public String MemberSignIn_detail
		(@RequestParam("memberEmail")String memberEmail,
		@RequestParam("memberPw")String memberPw,Model model)
		{
		for (int i = 0; i < svc.getList().size(); i++) {
			if (svc.getList().get(i).getMemberEmail().equals(memberEmail)&&
					svc.getList().get(i).getMemberPw().equals(memberPw)) {
					System.out.println("검증 입장 MemberSignIn");					
					session.setAttribute("memberEmail", memberEmail);
					
					return "thymeleaf/main/index";				
				}
		}
			
			 return "thymeleaf/login/MemberLoginForm";
		}
		
				
				
		@GetMapping("/family_sign_up_form")
		public String familyLoginForm()
		{
				   return "thymeleaf/login/familySignUpForm";
		}
		
		//familySignUpForm POST 형식으로 보낸다.
		@PostMapping("/family_form")
		public String mailCertificationForm(@RequestParam("delEmail")String delEmail,
											@RequestParam("familyPwd")String familyPwd,
											HttpSession session, Model model) {
			System.out.println("입장 family_form");
			
			svc.sendMineMessage(delEmail,familyPwd);
			System.out.println("입장 family_form");
			return "thymeleaf/login/mailCertificationForm";	
		}
		//회원 가입 화면 
		@PostMapping("/form_complate")
		public String familySignUpForm(@RequestParam("delEmail")String delEmail,
				@RequestParam("familyPwd")String familyPwd
				,Model model){
			
			try {	
					Long email_id=svc.findByDelEmailId(delEmail);
					session.setAttribute("email_id", email_id);
					return "thymeleaf/login/memberSignUpForm";
			} catch (Exception e) {
				//값이 없을경우 오류가 터졌을때
				svc.familySignUpForm(delEmail, familyPwd);
				return "thymeleaf/login/memberSignUpForm";
			}
		}	
		@PostMapping("/member_form")
		@ResponseBody
		public Map<String, Boolean> memberForm(Member member,Model model) {
			System.out.println("멤버폼 입장 memberForm");
			Map<String, Boolean> map = new HashMap<>();
			for (int i = 0; i < svc.getList().size(); i++) {
			if (svc.getList().get(i).getMemberEmail().equals(member.getMemberEmail())) {
				System.out.println("if false 입장");
				map.put("emailBoolean", false);
				return map;
			}
			}
			System.out.println("이메일 아이디 입장 "+ session.getAttribute("email_id"));
			Long email_id =(Long) session.getAttribute("email_id");
			member.setMemberAge((Integer)(member.getMemberAge()));
			map.put("emailBoolean", true);
			svc.memberSignUpForm(member,email_id);	
			return map;	
		}
		@GetMapping("/member_main")
		public String memberMain()
		{ 
			return "thymeleaf/login/MemberLoginForm";
		}
		
		
		//아이디 찾기 화면 
		@GetMapping("/lost_id_find_form")
		public String lostIdFindIdForm()
		{
			       return "thymeleaf/login/lostIdFindIdForm";
		}	
		//비밀번호 찾기 화면 
		@GetMapping("/lost_pw_find_form")
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