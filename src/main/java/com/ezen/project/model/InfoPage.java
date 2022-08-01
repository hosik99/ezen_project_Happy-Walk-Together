package com.ezen.project.model;

import java.sql.Date;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class InfoPage {

	private int pageNum;
	private String infoTitle;
	private String summary;
	private String contents;
	private Date wDate;
	private Date uDate;
	
}
