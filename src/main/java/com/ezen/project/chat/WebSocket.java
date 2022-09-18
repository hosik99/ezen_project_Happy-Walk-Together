package com.ezen.project.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.ezen.project.etc.HttpSessionConfigurator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@ServerEndpoint(value="/websocket", configurator=HttpSessionConfigurator.class)
public class WebSocket
{
    private static Map<String, Session> sessionMap = new HashMap<>();
    private static Map<String, List<String>> channelMap = new HashMap<>();
    private static Map<String, String> uidChanCode = new HashMap<>();
    
    @OnOpen
    public void handleOpen(Session session, EndpointConfig config) {
    	if (session != null) {
            HttpSession httpSession = (HttpSession) config.getUserProperties().get("session");
            String uid = (String) httpSession.getAttribute("memberEmail");
            String channelCode = (String) httpSession.getAttribute("channelCode");
            
            uidChanCode.put(uid, channelCode);
            
            List<String> chanUserlist =  channelMap.get(channelCode);
            if(chanUserlist==null) {
            	chanUserlist = new ArrayList();
            	chanUserlist.add(uid);
            	channelMap.put(channelCode, chanUserlist);
            }else {
            	for(int i=0;i<chanUserlist.size();i++) {
            		if(uid.equals(chanUserlist.get(i))) {
            			chanUserlist.remove(uid);
            		}
            	}
            	chanUserlist.add(uid);
            }
            sessionMap.put( uid, session);
            
            
            Map<String, Object> map = new HashMap<>();
            map.put("from", uid);
            map.put("contents", "");
            map.put("channelCode", channelCode);
            map.put("chanUserlist", chanUserlist);
            
            
            try {
				String jsStr = new ObjectMapper().writeValueAsString(map);
				sendMsgToChannel(jsStr);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
            
        }
    }

    @OnMessage
    public void handleMessage(String message, Session session) {

        if (session != null) {
            try {
			} catch (Exception e) {
				e.printStackTrace();
			}
            sendMsgToChannel(message);
        }
    }

    @OnClose
    public void handleClose(Session session) {
        if (session != null) {
        	String uid = getUserBySession(session);
        	String chanCode = uidChanCode.get(uid);
        	List<String> chanUserList = channelMap.get(chanCode);
        	chanUserList.remove(uid);
        	sessionMap.remove(uid);
        }
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }
    
    //그룹멤버들에게 메세지 전송
    private boolean sendMsgToChannel(String message) {
    	if (sessionMap == null) return false;
    	int sessionCount = sessionMap.size();
        if (sessionCount < 1) return false;
        
        String channelCode=null;
        try {
			Map<String,Object> map = new ObjectMapper().readValue(message, Map.class);
			channelCode = (String)map.get("channelCode");
			
			List<String> chanUserList = channelMap.get(channelCode);
			if(chanUserList==null) return false;
			map.put("chanUserlist", chanUserList);
			
	        for(int i=0;i<chanUserList.size();i++) {
	        	Session ss = sessionMap.get(chanUserList.get(i));
	        	if (ss == null) continue;
	        	if (!ss.isOpen()) continue;
	            
	        	String jsStr = new ObjectMapper().writeValueAsString(map);
	        	ss.getAsyncRemote().sendText(jsStr);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return true;
    }
    
    private String getUserBySession(Session ss)
    {
    	Set<String> keys = sessionMap.keySet();
        Iterator<String> itr = keys.iterator();
        
        while(itr.hasNext()) {
        	String key = itr.next();
        	Session _ss = sessionMap.get(key);
        	if(_ss==ss) return key;
        }
    	return null;
    }
}