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
	@Column(name="chat_id", insertable=false, nullable = false)
	private Long chatNum;
	
	private String hatTitle;
	
	private Date chatDate;
	
	private String chatAuthor;
}
