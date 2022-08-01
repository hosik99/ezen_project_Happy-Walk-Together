package com.ezen.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/petLogin")
public class WalkTLoginController {

	
		//로그인 화면 화면 
		@GetMapping("/loginForm")
		public String loginForm()
		{
			       return "thymeleaf/login/loginForm";
		}	
		//회원 가입 화면 
		@GetMapping("/signUpForm")
		public String signUpForm()
		{
			       return "thymeleaf/login/signUpForm";
		}	
		//아이디 찾기 화면 
		@GetMapping("/lostIdFindIdForm")
		public String lostIdFindIdForm()
		{
			       return "thymeleaf/login/lostIdFindIdForm";
		}	
		//비밀번호 찾기 화면 
		@GetMapping("/lostPwFIndPwForm")
		public String lostPwFIndPwForm()
		{
			       return "thymeleaf/login/lostPwFIndPwForm";
		}
		
		//아이디 찾기 메일 검증 화면 
		@GetMapping("/loginFindIdMail")
		public String loginFindIdMail()
		{
			       return "thymeleaf/login/loginForm";
		}	
		//비밀번호 찾기 메일 검증 화면 
		@GetMapping("/loginFindPwMail")
		public String loginFindPwMail()
		{
			       return "thymeleaf/login/signUpForm";
		}	
		//아이디 찾기 메일 완료 화면 
		@GetMapping("/complateLoginFindIdMail")
		public String complateLoginFindIdMail()
		{
			       return "thymeleaf/login/lostIdFindIdForm";
		}	
		//비밀번호 찾기 메일 완료 화면 
		@GetMapping("/complateLoginFindPwMail")
		public String complateLoginFindPwMail()
		{
			       return "thymeleaf/login/lostPwFIndPwForm";
		}
}
