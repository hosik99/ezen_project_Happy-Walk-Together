package com.ezen.project.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name="reports")
public class Report {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long num;
	
	private String reason;
	
	private String reportWhere;
	
	@NotEmpty
	private String contents;
	
	@NotEmpty
	private String reportId;
	
	private String writer;
	
	private Date wdate;
	
	private Integer readed=0;
}
