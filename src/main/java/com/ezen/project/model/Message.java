package com.ezen.project.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Entity
@Table(name="message")
public class Message {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="msg_num", insertable=false, nullable = false)
	private Long msgNum;
	
	private String sender;
	
	@NotEmpty
	private String receiver;
	
	@NotEmpty
	@Column(name="msg_title")
	private String msgTitle;
	
	@NotEmpty
	@Column(name="msg_contents")
	private String msgContents;
	
	@Column(name="msg_wdate")
	private Date msgWdate;
	
	private Integer readed;
}
