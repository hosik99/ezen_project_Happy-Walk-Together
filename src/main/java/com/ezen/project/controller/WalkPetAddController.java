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

import com.ezen.project.model.Family;
import com.ezen.project.model.Member;
import com.ezen.project.model.Pet;
import com.ezen.project.service.WalkPetAddService;
import com.ezen.project.service.WalkTLoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/pet_add")
public class WalkPetAddController {

	@Autowired
	private WalkPetAddService svc;


	@Autowired
	private HttpSession session;
	
	@GetMapping("/add_form")
	public String AddForm(Model model) {
		model.addAttribute("list", svc.getList());
		return "thymeleaf/mainPetAdd/mainPetAddForm";
	}
	
	@PostMapping("/add_detail")
	@ResponseBody
	public Map<String, Boolean> AddDetail(Pet pet,Model model) {
		Map<String, Boolean> map = new HashMap<>();	
		System.out.println("펫 아이디 입장 "+ session.getAttribute("memberEmail"));
		Family memberFamilyId =svc.findByDelEmailId((String)session.getAttribute("memberEmail"));;
		System.out.println("펫 비밀번호 입장 "+memberFamilyId.getFamilyId());
		boolean petBoolean=svc.PetAddForm(memberFamilyId.getFamilyId(),pet) != null;
		map.put("petBoolean", petBoolean);
		return map;	
	}
	//삭제
	@PostMapping("/deleted")
	@ResponseBody
	public Map<String, Object> pageDeleteDetail(@RequestParam("petId")Long petId) {
		Boolean deleted = svc.delete(petId);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("deleted", deleted);
		return map;
	}
	@PostMapping("/updated")
	@ResponseBody
	public Map<String, Object> Update_form(@RequestParam(name="petId")Long petId,Model model) {
		List<Pet> edit = svc.findByPetId(petId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name",  edit.get(0).getPetName());
		map.put("breed",  edit.get(0).getBreed());
		map.put("petId",  edit.get(0).getPetId());
		return map;
	}
	
	
	@PostMapping("/update_detail")
	@ResponseBody
	public Map<String,Object> updateDetail(Pet pet) {
		System.out.println(
				pet.getPetName()+"/"+pet.getPetId()+"/"+
				pet.getPetSize()+"/"+pet.getBreed());
		boolean update = svc.update(pet);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("update", update);
		return map;	
	}
	
}
