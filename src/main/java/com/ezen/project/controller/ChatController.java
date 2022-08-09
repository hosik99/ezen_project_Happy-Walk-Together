package com.ezen.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.project.model.ChatChannel;
import com.ezen.project.model.Message;
import com.ezen.project.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/ws")
public class ChatController
{
	//private String uid;
	
	@Autowired
	private ChatService svc;
	@Autowired
	private HttpSession session;
	/*
	@Autowired
	public void setting(HttpSession session){
		if(session.getAttribute("uid")!=null) {
			uid = (String)session.getAttribute("uid");
			System.out.println("uid: "+uid);
		
		}
	}
	*/
	
	//채팅방 리스트 
	@GetMapping("/chat/list")
    public String chatList(Model model) {
		String userId = (String)session.getAttribute("memberEmail");
		System.out.println("userId: "+userId);
		List<ChatChannel> voList = svc.findByUserId(userId);
		model.addAttribute("list",voList);
		return "thymeleaf/chat/chatList";
    }
	
	//채팅방 생성 후 다른 사람 초대 //detail페이지에서 사용
	@PostMapping("/chat/crate/invite")
	@ResponseBody
	public Map<String,String> crateAndInvite(@RequestParam("adder")String adder,@RequestParam("channelTitle")String channelTitle) {
		String userId = (String)session.getAttribute("memberEmail");	//초대한 사람
		ChatChannel obj = svc.createChannel(userId,channelTitle);	//초대한 사람 & 제목 DB에 저장
		Map<String,String> map = new HashMap<String,String>();
		if(obj.getChannelCode()!=null) {
			String entered = svc.enterChannel(obj.getChannelCode(),adder,userId);	//ChannelCode에 초대받은 사람 DB에 저장
			if("checkFalse".equals(entered)) {
				map.put("entered","false");
			}else {
				map.put("entered",entered);
			}
			map.put("channelCode", obj.getChannelCode());
		}
		return map;
	}
	
	//채팅방 만들기
	@PostMapping("/chat/crate/channelCode")
	public String crateChannelCode(@RequestParam("channelTitle")String channelTitle) {
		String userId = (String)session.getAttribute("memberEmail");
		ChatChannel obj = svc.createChannel(userId,channelTitle);
		return obj.getChannelCode()!=null ? "redirect:/ws/chat/list" : "errorPage";
	}
	
	//채팅 창으로 이동
	@Transactional
	@GetMapping("/chat/{chatNum}/{channelCode}")
    public String chat(@PathVariable("channelCode")String channelCode,@PathVariable("chatNum")int chatNum,Locale locale) {
		svc.readed(chatNum);
		session.setAttribute("channelCode",channelCode);
		return "chat/chat";
    }
	
	//이미 있는 채팅방에 초대
	@PostMapping("/invite/channel")
	@ResponseBody
	public String inviteChannel(@RequestParam("adder")String adder,@RequestParam("channelCode")String channelCode){
		String sender = (String)session.getAttribute("memberEmail");
		String reChannelCode = channelCode.replace("\"","");
		String added =svc.enterChannel(reChannelCode,adder,sender);
		return added;
	}
	
	//채팅방 이름 바꾸기
	@Transactional
	@PostMapping("/update/title")
	@ResponseBody
	public String updateTitle(@RequestParam("updateTitle")String updateTitle,@RequestParam("channelCode")String channelCode)
	{
		String userId = (String)session.getAttribute("memberEmail");
		String reChannelCode = channelCode.replace("\"","");
		boolean updated = svc.updateTitle(userId,reChannelCode,updateTitle);
		return updated+"";
	}
	
	//채팅방 삭제
	@Transactional
	@PostMapping("/delete/channel")
	@ResponseBody
	public String deleteChannel(@RequestParam("channelCode")String channelCode)
	{
		String userId = (String)session.getAttribute("memberEmail");
		String reChannelCode = channelCode.replace("\"","");
		boolean deleted = svc.deleteChannel(userId,reChannelCode);
		return deleted+"";
	}
	
	
}
