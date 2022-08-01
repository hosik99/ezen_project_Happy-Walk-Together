package com.ezen.project.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Entity
@Table(name="message")
public class Message {
	@Id
	private int num;
	
	private String sender;
	
	@NotEmpty
	private String receiver;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String contents;
	
	private Date writeDate;
	
	private Integer readed;
}
