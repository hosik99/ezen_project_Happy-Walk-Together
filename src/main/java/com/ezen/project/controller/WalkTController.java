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
	
	//소개 화면 
	@GetMapping("/introduceIndex")
    public String introduceIndex()
    {	
       return "thymeleaf/introduceIndex";
    }
	//메인 화면 
	@GetMapping("/mainIndex")
	public String mainIndex()
	{
	       return "thymeleaf/mainIndex";
	}
	
		
	
	//펫 채팅 리스트 화면
	@GetMapping("/petMeetChatList")
	public String petMeetChatList()
	{
		       return "thymeleaf/mainChat/petMeetChatList";
	}
	//펫 채팅 상세보기 화면 
	@GetMapping("/petMeetChatDetail")
	public String petMeetChatDetail(@RequestParam("num")int num)
	{
		       return "thymeleaf/mainChat/petMeetChatDetail";
	}
	
	
	//펫 공지사항 화면
	@GetMapping("/infoPageList")
	public String infoPageList()
	{
		       return "thymeleaf/mainInfoPage/infoPageList";
	}
	//펫 공지사항 상세보기 화면 
	@GetMapping("/infoPageDetail")
	public String infoPageDetail(@RequestParam("num")int num)
	{
		       return "thymeleaf/mainInfoPage/infoPageDetail";
	}
	
	
	
	//펫 쪽지함 화면 
	@GetMapping("/msgList")
	public String msgList()
	{
		       return "thymeleaf/mainMsg/msgList";
	}	
	//펫 쪽지함 상세보기 화면 
	@GetMapping("/msgDetail")
	public String msgDetail(@RequestParam("num")int num)
	{
		       return "thymeleaf/mainMsg/msgDetail";
	}
	
	
	//펫 만남 게시판 화면
	@GetMapping("/petBoardList")
	public String petBoardList()
	{
			   return "thymeleaf/mainPetBoard/petBoardList";
	}	
	//펫 만남 상세보기 화면 
	@GetMapping("/petBoardDetail")
	public String petBoardDetail(@RequestParam("num")int num)
	{
		       return "thymeleaf/mainPetBoard/petBoardDetail";
	}
	
	
	//펫 회원용 사진 게시판 화면 
	@GetMapping("/petPictureList")
	public String petPictureList()
	{
		       return "thymeleaf/mainPetPicture/mainIndex";
	}
	//펫 회원용 사진 상세보기 화면 
	@GetMapping("/petPictureDetail")
	public String petPictureDetail(@RequestParam("num")int num)
	{
		       return "thymeleaf/mainPetPicture/petPictureDetail";
	}
	

	
	
}
