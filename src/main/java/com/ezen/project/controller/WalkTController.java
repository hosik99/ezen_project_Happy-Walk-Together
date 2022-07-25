package com.ezen.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ezen.project.service.WalkTService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/pet")
public class WalkTController {

	@Autowired
	private WalkTService svc;
	@Autowired
	private HttpSession session;
	
	@GetMapping("/login_form")
    public String login_Form()
    {
       return "thymeleaf/Login/UserLogin";
    }
	@PostMapping("/login_detail")
    @ResponseBody
    public Map<String, Object> login_Detail(@RequestParam("uid")String uid,
    		@RequestParam("upw")String upw) {
		
		Map<String, Object> map = new HashMap<String, Object>();
    	if (uid!="" &&  upw!="") {
    		session.setAttribute("uid", uid);
    		session.setAttribute("upw", upw);
    		map.put("uid", uid);
    		map.put("upw", upw);
    		map.put("login", true);
    		return map;
		}
    	map.put("login", false);
		return map;
    }
	@PostMapping("/logout")
    public String logout()
    {
	   session.invalidate();
       return "thymeleaf/Login/UserLogin";
    }
	
	@GetMapping("/list_form")
	public String list_form(Model model) {	
		model.addAttribute("uid",session.getAttribute("uid"));
		return "thymeleaf/MainList";
	}
	
	@PostMapping("/list_detail")
	@ResponseBody
	public Map<String, Object> list_detail(Model model) {	
		Map<String, Object> map = new HashMap<>();
		if (session.getAttribute("uid") == null) {
			map.put("bookey", false);
			return map;
		}
		model.addAttribute("uid",session.getAttribute("uid"));
		map.put("bookey", true);
		return map;
	}
	
}
