package com.ezen.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.project.service.WalkTService;

@Controller
@RequestMapping("/pet")
public class WalkTController {

	@Autowired
	private WalkTService svc;
	
	@GetMapping("/loginForm")
    public String login_form()
    {
       return "thymeleaf/Login/UserLogin";
    }
	
}
