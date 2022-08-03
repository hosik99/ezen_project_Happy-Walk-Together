package com.ezen.project.model;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Table(name="info_board")
public class InfoPage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pageNum;
	
	private String infoTitle;
	
	private String summary;
	
	private String contents;
	
	private Date wDate;
	
	private Date uDate;
	
}
