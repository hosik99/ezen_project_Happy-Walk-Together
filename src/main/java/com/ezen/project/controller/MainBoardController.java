package com.ezen.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.project.dto.MainBoardDTO;
import com.ezen.project.dto.PageRequestDTO;
import com.ezen.project.service.MainBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/pet/mainboard")
@RequiredArgsConstructor
@Slf4j
public class MainBoardController {
	@Autowired
	MainBoardService svc;
	
	@GetMapping("/register")
	public String register() {
		log.info("register get...");
		return "thymeleaf/mainPetBoard/petBoardRegister";
	}
	
	@PostMapping("/register")
	public String registerPost(MainBoardDTO dto, RedirectAttributes redirectAttributes) {
		log.info("dto={}", dto);
		//새로 추가된 엔티티의 번호
		Long mainBoardNum = svc.register(dto);
		
		log.info("MainBoardNum={}", mainBoardNum);
		
		redirectAttributes.addFlashAttribute("msg", mainBoardNum);
		
		return "redirect:/pet/mainboard/list";
	}
	
	@GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model){

        log.info("list............." + pageRequestDTO);

        model.addAttribute("result", svc.getList(pageRequestDTO));
        
        return "thymeleaf/mainPetBoard/petBoardList";

    }
	
	@GetMapping("/read")
	public String read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long num, Model model) {
		log.info("num: {}", num);
		MainBoardDTO mainBoardDTO = svc.get(num);
		log.info("{}", mainBoardDTO);
		model.addAttribute("dto", mainBoardDTO);
		return "thymeleaf/mainPetBoard/petBoardRead";
	}
	
	@GetMapping("/modify")
	public String modify(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long num, Model model) {
		log.info("num: {}", num);
		MainBoardDTO mainBoardDTO = svc.get(num);
		log.info("{}", mainBoardDTO);
		model.addAttribute("dto", mainBoardDTO);
		return "thymeleaf/mainPetBoard/petBoardModify";
	}

    @PostMapping("/remove")
    public String remove(@RequestParam("mainBoardNum") Long num, RedirectAttributes redirectAttributes){


        log.info("num: " + num);

        svc.removeWithReplies(num);

        redirectAttributes.addFlashAttribute("msg", num);

        return "redirect:/pet/mainboard/list";

    }

    @PostMapping("/modify")
    public String modify(MainBoardDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes){


        log.info("post modify.........................................");
        log.info("dto: " + dto);

        svc.modify(dto);

        redirectAttributes.addAttribute("page",requestDTO.getPage());
        redirectAttributes.addAttribute("type",requestDTO.getType());
        redirectAttributes.addAttribute("keyword",requestDTO.getKeyword());

        redirectAttributes.addAttribute("num", dto.getMainBoardNum());

        return "redirect:/pet/mainboard/read";

    }
}
