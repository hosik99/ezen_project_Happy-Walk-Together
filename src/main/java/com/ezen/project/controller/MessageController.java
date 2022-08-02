package com.ezen.project.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.project.model.Message;
import com.ezen.project.service.MsgService;


import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/msg")
public class MessageController {
	
	@Autowired
	private MsgService svc;
	@Autowired
	private HttpSession session;
	
	//private String id = (String)session.getAttribute("id");
	private String id = "aa1";
	
	@GetMapping("/msgForm")
	public String msgForm(Model model)
	{
		model.addAttribute("msg",new Message());
		return "thymeleaf/messageFolder/messageForm";
	}
	
	@PostMapping("/addMsgInfo")
	@ResponseBody
	public boolean addMsgInfo(Message msg)
	{	
		System.out.println(msg.toString());
		if(msg.getReceiver()=="" | msg.getMsgTitle() =="" | msg.getMsgContents()=="") {
			return false;
		}
		msg.setSender(id);
		boolean addMsg = svc.addMsgInfo(msg);
		return addMsg;
	}
	
	@GetMapping("/getMsgByReceiver")
	public String getMsgByReceiver(Model model)
	{
		List<Message> list = svc.getMsgByReceiver(id);
		model.addAttribute("list",list);
		return "thymeleaf/messageFolder/showMsgList";
	}
	
	@GetMapping("/getDetailMsg/{userNum}")
	public String getDetailMsg(@PathVariable("userNum")int userNum,Model model)
	{
		Message msg = svc.getDetailMsg(userNum);
		model.addAttribute("msg",msg);
		return "thymeleaf/messageFolder/showUserDetail";
	}
	
	@GetMapping("/deleteMsg/{msgNum}")
	public String deleteMsg(@PathVariable("msgNum")int msgNum)
	{
		svc.deleteMsg(msgNum);
		return "redirect:/msg/getMsgByReceiver";
	}
}
