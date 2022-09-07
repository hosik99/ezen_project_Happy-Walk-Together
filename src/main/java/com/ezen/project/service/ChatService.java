package com.ezen.project.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.project.Repository.ChatRepository;
import com.ezen.project.Repository.MsgRepository;
import com.ezen.project.etc.MsgManager;
import com.ezen.project.model.ChatChannel;
import com.ezen.project.model.Message;

@Service
public class ChatService {

	@Autowired
	private ChatRepository rps;
	@Autowired
	private MsgManager msgManager;
	
	
	public ChatChannel createChannel(String user,String channelTitle) {
		ChatChannel obj = new ChatChannel();
		obj.setUserId(user);
		obj.setCreateDate(returnDate());
		obj.setChannelCode(randomCode());
		obj.setChannelTitle(channelTitle);
		ChatChannel obj_2 = rps.save(obj);
		return obj_2;
	}
	
	public String enterChannel(String chatChannel,String adder,String sender) {
		Integer check = rps.existCheck(chatChannel,adder);
		if(check!=0) return "checkFalse";
		
		ChatChannel obj = new ChatChannel();
		obj.setUserId(adder);
		obj.setChannelCode(chatChannel);
		ChatChannel obj_2 = rps.save(obj);
		
		Message msg = getPreMsg(adder,sender);
		msgManager.addMsgInfo(msg);
		
		return obj_2.getChannelCode().equals(chatChannel)+"";
	}

	public List<ChatChannel> findByUserId(String userId) {
		List<ChatChannel> voList = rps.findByUserIdOrderByChatNumDesc(userId);
		if(voList!=null) return voList;
		return null;
	}

	public boolean updateTitle(String userId, String reChannelCode, String updateTitle) {
		int n =rps.updateTitle(userId,reChannelCode,updateTitle);
		boolean updated = n>0 ?  true : false;
		return updated;
	}

	public boolean deleteChannel(String userId, String reChannelCode) {
		int n = rps.deleteChannel(userId,reChannelCode);
		boolean deleted = n>0 ?  true : false;
		return deleted;
	}

	public boolean readed(int chatNum) {
		try {
			rps.readed(chatNum);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private Message getPreMsg(String adder, String sender) {
		Message msg = new Message();
		msg.setSender(sender);
		msg.setReceiver(adder);
		msg.setTitle(sender+"님으로 부터 초대되었습니다.");
		msg.setContents(returnDate()+"에 초대 되었습니다.");
		return msg;
	}

	private Date returnDate() {
		return Date.valueOf(LocalDate.now());
	}
	
	private String randomCode() {
		UUID randomUUID = UUID.randomUUID();	
		return randomUUID.toString().replaceAll("-","");
	}
}
