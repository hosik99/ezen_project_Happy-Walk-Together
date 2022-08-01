package com.ezen.project.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.project.Repository.MsgRepository;
import com.ezen.project.etc.MsgManager;
import com.ezen.project.model.Message;



@Service
public class MsgService {

	@Autowired
	private MsgManager mgr;
	
	@Autowired
	private MsgRepository msgRepository;
	
	@Autowired
	public void setSctx(ServletContext sctx){
		sctx.setAttribute("svc", this);
		List<Message> list = getAllMsg();
		if(list==null) {
			list = new ArrayList<Message>();
		}
		sctx.setAttribute("msgList", list);
	}
	
	public boolean addMsgInfo(Message msg) {
		return mgr.addMsgInfo(msg);
	}

	public List<Message> getAllMsg()
	{
		return msgRepository.findAll();
	}

	public List<Message> getMsgByReceiver(String receiver) {
		return mgr.getMsgByReceiver(receiver);
	}

	public void saveMsg(List<Message> list) {
		msgRepository.saveAll(list);
	}

	public List<Message> getAllList() {
		return msgRepository.findAll();
	}

	public Message getDetailMsg(int userNum) {
		return mgr.getDetailMsg(userNum);
	}

	public boolean deleteMsg(int msgNum) {
		return mgr.deleteMsg(msgNum);
	}

	public void deleteAll() {
		msgRepository.deleteAll();
	}
	
}
