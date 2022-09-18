package com.ezen.project.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="report_imgs")
public class ReportImg {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long num;
	
	private String fpath;	
	private String fname;	
	
	private Date udate;
	
	private Long pnum; 
}
