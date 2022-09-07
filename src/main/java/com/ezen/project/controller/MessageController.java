package com.ezen.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
	@GetMapping("/msgForm")
	public String msgForm(Model model){
		model.addAttribute("msg",new Message());
		return "thymeleaf/messageFolder/messageForm";
	}
	
	@PostMapping("/addMsgInfo")
	@ResponseBody
	public String addMsgInfo(Message msg){	
		if(msg.getReceiver()=="") return "nllReceiver";
		if(msg.getTitle() =="") return "nullTitle";
		if(msg.getContents()=="") return "nullContents";
		msg.setSender(getMemberEmail());
		boolean addMsg = svc.addMsgInfo(msg);
		return addMsg+"";
	}
	
	@GetMapping("/getMsgByReceiver")
	public String getMsgByReceiver(Model model){
		List<Message> list = svc.getMsgByReceiver(getMemberEmail());
		model.addAttribute("list",list);
		return "thymeleaf/messageFolder/showMsgList";
	}
	
	@GetMapping("/getDetailMsg/{userNum}")
	public String getDetailMsg(@PathVariable("userNum")int userNum,Model model){
		Message msg = svc.getDetailMsg(userNum);
		model.addAttribute("msg",msg);
		return "thymeleaf/messageFolder/showMsgDetail";
	}
	
	@GetMapping("/deleteMsg/{msgNum}")
	public String deleteMsg(@PathVariable("msgNum")int msgNum){
		svc.deleteMsg(msgNum);
		return "redirect:/msg/getMsgByReceiver";
	}
	
	//신고 관리 페이지를 통한 접속
	@GetMapping("/msgForm/{member}")
	public String msgFormByUsercontorll(Model model,@PathVariable("member")String member){
		model.addAttribute("msg",getPreMsg(member));
		return "thymeleaf/messageFolder/messageForm";
	}
	
	
	private Message getPreMsg(String member) {
		String title = "경고 메시지";
		String contents = "경고 메시지\n\n"
				+ "다수의 신고가 접수 될 시에 영구정지 될 수 있습니다.";
		Message msg = new Message();
		msg.setReceiver(member);
		msg.setTitle(title);
		msg.setContents(contents);
		return msg;
	}
	
	private String getMemberEmail() {
		String memberEmail = (String)session.getAttribute("memberEmail");
		return memberEmail;
	}
	
}
