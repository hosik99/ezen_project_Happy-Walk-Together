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
		@PostMapping("/form")
		public String mailCertificationForm(@RequestParam("del_email")String del_email,
											@RequestParam("family_pwd")String family_pwd,
											HttpSession session) {
			
			rendom = new Random();	
			int rd = (Integer)rendom.nextInt(100000);
			cerNum = rd;
			svc.sendMineMessage(del_email,family_pwd,rd);	
			return "thymeleaf/login/mailCertificationForm";	
		}
		//회원 가입 화면 
		@PostMapping("/form_complate/{del_email}/{family_pwd}/{rd}")
		public String familySignUpForm(@PathVariable("del_email")String del_email,
				@PathVariable("family_pwd")String family_pwd,
				@PathVariable("rd")int rd
				,Model model
				,HttpSession session){
			if (rd == cerNum) {
				//첫 패밀리
				svc.familySignUpForm(del_email,family_pwd);				
				return "thymeleaf/login/memberSignUpForm";
			}
			return null;  
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
