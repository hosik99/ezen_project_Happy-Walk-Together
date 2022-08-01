/*
package com.ezen.project;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyApplicationContextListener implements ApplicationListener
{

	
   @Override
   public void onApplicationEvent(ApplicationEvent event) 
   {
	  
      //log.info("애플리케이션 이벤트 발생={}", event);
      if(event instanceof ContextClosedEvent)
      {
         log.info("애플리케이션 종료 이벤트");
      }
      else if(event instanceof ContextRefreshedEvent)
      {
         log.info("애플리케이션 리프레시 이벤트");
         ContextRefreshedEvent e = (ContextRefreshedEvent) event;
       
          ApplicationContext appContext = e.getApplicationContext();
          if (!(appContext instanceof WebApplicationContext)) return;
          
          WebApplicationContext ctx = (WebApplicationContext) e.getApplicationContext();
          ServletContext context = ctx.getServletContext();

        
      }
   }
}
*/