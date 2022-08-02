package com.ezen.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mate")
public class MateSearchController {
	
	@GetMapping("/mateList")
	public String getListByPage(Model model) {
		return null;
	}
}
