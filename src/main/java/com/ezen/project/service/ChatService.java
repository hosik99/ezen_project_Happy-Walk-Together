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
import com.ezen.project.model.ChatChannel;
import com.ezen.project.model.Message;

@Service
public class ChatService {

	@Autowired
	private ChatRepository rps;
	
	public ChatChannel createChannel(String user,String channelTitle) {
		ChatChannel obj = new ChatChannel();
		obj.setUserId(user);
		obj.setCreateDate(returnDate());
		obj.setChannelCode(randomCode());
		obj.setChannelTitle(channelTitle);
		ChatChannel obj_2 = rps.save(obj);
		return obj_2;
	}
	
	public String enterChannel(String chatChannel,String adder) {
		Integer check = rps.existCheck(chatChannel,adder);
		System.out.println("check: "+check);
		if(check!=0) return "checkFalse";
		ChatChannel obj = new ChatChannel();
		obj.setUserId(adder);
		obj.setChannelCode(chatChannel);
		ChatChannel obj_2 = rps.save(obj);
		return obj_2.getChannelCode().equals(chatChannel)+"";
	}
	
	private Date returnDate() {
		return Date.valueOf(LocalDate.now());
	}
	private String randomCode() {
		UUID randomUUID = UUID.randomUUID();	
		return randomUUID.toString().replaceAll("-","");
	}

	public List<ChatChannel> findByUserId(String userId) {
		List<ChatChannel> voList = rps.findByUserIdOrderByChatNumDesc(userId);
		if(voList!=null) {
//			for(int i=0;i<voList.size();i++) {
//				ChatChannel vo = voList.get(i);
//				if(vo.getChannelTitle()==null) vo.setChannelTitle("<새로 초대받은 채팅방 입니다.>");
//			}
			return voList;
		}
		return null;
	}

	public boolean updateTitle(String userId, String reChannelCode, String updateTitle) {
		try {
			rps.updateTitle(userId,reChannelCode,updateTitle);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteChannel(String userId, String reChannelCode) {
		int deleted = rps.deleteChannel(userId,reChannelCode);
		if(deleted>0) {
			return true;
		}
		return false;
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

//	public Map<String,List<String>> memberList(List<ChatChannel> voList) {
//		Map<String,List<String>> memberListMap = new HashMap();
//		for(int i=0;i<voList.size();i++) {
//			String channelCode = voList.get(i).getChannelCode();
//			List<String> memberList = rps.getMemberList(channelCode);
//			memberListMap.put(channelCode, memberList);
//		}
//		return memberListMap;
//	}
}
