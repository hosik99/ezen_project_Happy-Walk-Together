package com.ezen.project.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Table(name="board")
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) // JPA에서 자동증가 지원
	private int num;
	
	private String title;
	private String contents;
	private String author;
	private Date wDate;
	private String fPath;
	
}
