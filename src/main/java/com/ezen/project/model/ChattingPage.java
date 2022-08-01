package com.ezen.project.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ChattingPage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="chat_num", insertable=false, nullable = false)
	private Long chatNum;
	
	@Column(name="chat_title")
	private String chatTitle;
	
	@Column(name="chat_date")
	private Date chatDate;
	
	@Column(name="chat_author")
	private String chatAuthor;
}
