package com.ezen.project.model;

import java.sql.Date;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class InfoPage {

	
	private int page_num;
	private String info_title;
	private String summary;
	private String contents;
	private Date w_date;
	private Date u_date;
	
}
