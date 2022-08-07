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
    
    @OnOpen
    public void handleOpen(Session session, EndpointConfig config) {
        System.out.println("sessionMap: "+sessionMap.toString());
        System.out.println("channelMap: "+channelMap.toString());
    	if (session != null) {
            HttpSession httpSession = (HttpSession) config.getUserProperties().get("session");
            String uid = (String) httpSession.getAttribute("memberEmail");
            if(uid!=null) {
            	String str[] = uid.split("@");
            	uid = str[0];
            }
            System.out.println("uid: "+uid);
            String channelCode = (String) httpSession.getAttribute("channelCode");
            
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
            
            
            Map<String, String> map = new HashMap<>();
            map.put("from", uid);
            map.put("contents", "");
            map.put("channelCode", channelCode);
            
            
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
            //String sessionId = session.getId();
        	String uid = getUserBySession(session);
            
            /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
            Map<String, String> map = new HashMap<>();
            map.put("from", uid);
            map.put("contents", "disconnected");
            
            try {
				String jsStr = new ObjectMapper().writeValueAsString(map);
				sendMsgToChannel(jsStr);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
            
        }
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }
    
    
//    /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
//    private boolean sendMessageToAll(String message) {
//        if (sessionMap == null) {
//            return false;
//        }
//
//        int sessionCount = sessionMap.size();
//        if (sessionCount < 1) {
//            return false;
//        }
//        
//        Set<String> keys = sessionMap.keySet();
//        Iterator<String> itr = keys.iterator();
//        
//        while(itr.hasNext()) {
//        	String key = itr.next();
//        	Session ss = sessionMap.get(key);
//        	if (ss == null) {
//                continue;
//            }
//        	if (!ss.isOpen()) {
//                continue;
//            }
//        	ss.getAsyncRemote().sendText(message);
//        }
//        return true;
//    }
    
    private boolean sendMsgToChannel(String message) {
    	if (sessionMap == null) return false;
    	int sessionCount = sessionMap.size();
        if (sessionCount < 1) return false;
        
        String channelCode=null;
        try {
			Map<String,String> map = new ObjectMapper().readValue(message, Map.class);
			channelCode = map.get("channelCode");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        List<String> chanUserList = channelMap.get(channelCode);
        
        if(chanUserList==null) return false;
        System.out.println("chanUserList: "+chanUserList.toString());
        for(int i=0;i<chanUserList.size();i++) {
        	Session ss = sessionMap.get(chanUserList.get(i));
        	if (ss == null) {
                continue;
            }
        	if (!ss.isOpen()) {
                continue;
            }
        	ss.getAsyncRemote().sendText(message);
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