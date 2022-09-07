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
@Table(name="CNT_REPORT")
public class CntReport {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long num;
	
	private String member;
	
	private Date wdate;
	
	private String reason;
	
}
