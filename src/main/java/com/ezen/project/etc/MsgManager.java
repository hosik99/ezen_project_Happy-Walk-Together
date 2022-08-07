package com.ezen.project.etc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ezen.project.model.Message;

@Component
public class MsgManager {
	private ServletContext sctx;
	private String key = "msgList";
	
	@Autowired
	public void setSctx(ServletContext sctx)
	{
		this.sctx = sctx;
		if(this.sctx.getAttribute(key)==null)
		{
			List<Message> list = new ArrayList<>();
			sctx.setAttribute(key, list);
		}
	}
	
	public boolean addMsgInfo(Message msg) {
		msg.setWriteDate(java.sql.Date.valueOf(LocalDate.now()));
		msg.setReaded(0);
		msg.setNum((long) 1);
		Object obj = this.sctx.getAttribute(key);
		if(obj!=null) 
		{
			List<Message> list = (List<Message>) obj;
			Long lastNum;
			if(list.size()!=0) {
				lastNum = list.get(list.size()-1).getNum();
				msg.setNum(lastNum+1);
			}
			list.add(msg);
			return true;
		}
		return false;
		
	}

	public List<Message> getMsgByReceiver(String receiver) {
		List<Message> receivedList = new ArrayList();
		List<Message> list = getList();
		for(int i=0;i<list.size();i++){
			Message msg = list.get(i);
			if(receiver.equals(msg.getReceiver())){
				receivedList.add(msg);
			}
		}
		return receivedList;
	}
	

	public List<Message> getList(){
		Object obj = this.sctx.getAttribute(key);
		if(obj!=null) 
		{
			List<Message> list = (List<Message>) obj;
			return list;
		}
		return null;
	}

	public Message getDetailMsg(int userNum) {
		List<Message> list = getList();
		if(list==null) return null;
		for(int i=0;i<list.size();i++) {
			Message msg = list.get(i);
			if(userNum==msg.getNum()) {
				msg.setReaded(1);
				return msg;
			}
		}
		return null;
	}

	public boolean deleteMsg(int msgNum) {
		List<Message> list = getList();
		int originListSize = list.size();
		for(int j=0;j<list.size();j++) {
			if(msgNum==list.get(j).getNum()) {
				list.remove(j);
			}
		}
		return list.size()!=originListSize;
	}

}
