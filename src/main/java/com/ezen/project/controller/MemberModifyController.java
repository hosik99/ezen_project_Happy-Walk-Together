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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.project.Repository.MemberRepository;
import com.ezen.project.model.Member;
import com.ezen.project.model.Pet;
import com.ezen.project.service.MemberModifyService;
import com.ezen.project.service.WalkPetAddService;

@Controller
@RequestMapping("/member_modify")
public class MemberModifyController {
	
	@Autowired
	private MemberModifyService svc;


	@Autowired
	private HttpSession session;

	@PostMapping("/updated")
	@ResponseBody
	public Map<String, Object> Update_form(@RequestParam(name="memberEmail")String memberEmail,Model model) {
		System.out.println(memberEmail+"입장");
		
		List<Member> edit = svc.findByMemberEmail(memberEmail);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberPw",  edit.get(0).getMemberPw());
		map.put("memberName",  edit.get(0).getMemberName());
		map.put("memberAge",  edit.get(0).getMemberAge());
		map.put("birthday",  edit.get(0).getBirthday());
		map.put("gender",  edit.get(0).getGender());
		map.put("memberPhoneNumber",  edit.get(0).getMemberPhoneNumber());
		
		map.put("memberEmail",  edit.get(0).getMemberEmail());
		
		return map;
	}
	
	@PostMapping("/update_detail")
	@ResponseBody
	public Map<String,Object> updateDetail(Member member) {
		System.out.println(
				member.getMemberId()+"/"+member.getMemberEmail()+"/"+
				member.getMemberAge()+"/"+member.getMemberPhoneNumber());
		boolean update = svc.update(member);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("update", update);
		return map;	
	}
	
	@RequestMapping(value = "/logout")
	public String Logout() {
		session.invalidate();
		return "thymeleaf/introduce/index";
	}
	
}
