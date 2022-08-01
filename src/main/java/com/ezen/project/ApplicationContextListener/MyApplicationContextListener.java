package com.ezen.project.ApplicationContextListener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.ezen.project.model.Message;
import com.ezen.project.service.MsgService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component	
public class MyApplicationContextListener implements ApplicationListener{
	
	private ServletContext sctx;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) 
	{
		if(event instanceof ContextClosedEvent)
		{
			log.info("애플리케이션 종료 이벤트");
			MsgService svc = (MsgService)this.sctx.getAttribute("svc");
			List<Message> list = (List<Message>)this.sctx.getAttribute("msgList");
			svc.deleteAll();
			svc.saveMsg(list);	
			log.info("종료전 메시지를 테이블에 저장함");
		}
		else if(event instanceof ContextRefreshedEvent)	
		{
			log.info("애플리케이션 리프레시 이벤트");
			ContextRefreshedEvent e = (ContextRefreshedEvent) event;
			ApplicationContext appContext = e.getApplicationContext();
			 
		    if (!(appContext instanceof WebApplicationContext)) return;
		    
		    WebApplicationContext ctx = (WebApplicationContext) e.getApplicationContext();
		    ServletContext context = ctx.getServletContext();
		    this.sctx = context;
		    initContext(context);	//"msgList" 추가
		}
	}
	
	private void initContext(ServletContext context)
	{
		MsgService svc = (MsgService)this.sctx.getAttribute("svc");
		List<Message> list = svc.getAllList();
		context.setAttribute("msgList", list);
	}
}
