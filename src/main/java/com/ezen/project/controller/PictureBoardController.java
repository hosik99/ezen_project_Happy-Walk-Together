package com.ezen.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.project.dto.PageRequestDTO;
import com.ezen.project.dto.PictureBoardDTO;
import com.ezen.project.service.PictureBoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/pet/pictureboard")
@Slf4j
public class PictureBoardController {

	@Autowired
	PictureBoardService svc;
	
	@GetMapping("/list")
	public String list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("pageRequestDTO: {}", pageRequestDTO);
		
		model.addAttribute("result", svc.getList(pageRequestDTO));
		
		return "thymeleaf/mainPetPicture/petPictureList";
	}
	
	@GetMapping("/read")
	public String read(long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		log.info("bno: {}", bno);
		
		PictureBoardDTO pictureBoardDTO = svc.getPictureBoard(bno);
		
		model.addAttribute("dto", pictureBoardDTO);
		return "thymeleaf/mainPetPicture/petPictureRead";
	}
	
	@GetMapping("/momdify")
	public String modify(long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		log.info("bno: {}", bno);
		
		PictureBoardDTO pictureBoardDTO = svc.getPictureBoard(bno);
		
		model.addAttribute("dto", pictureBoardDTO);
		return "thymeleaf/mainPetPicture/petPictureModify";
	}
	
	@GetMapping("/register")
	public String register() {
		return "thymeleaf/mainPetPicture/petPictureRegister";
	}
	
	@PostMapping("/register")
	public String register(PictureBoardDTO boardDTO, RedirectAttributes redirectAttributes) {
		log.info("pictureBoardDTO: {}", boardDTO);
		Long bno = svc.register(boardDTO);
		redirectAttributes.addFlashAttribute("msg", bno);
		return "redirect:/pet/pictureboard/list";
	}
	
	@GetMapping("/test")
	public String test() {
		return "thymeleaf/mainPetPicture/test";
	}
}
