package com.ezen.project.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@Entity
@Table(name="board")
public class PetBoard {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) // JPA에서 자동증가 지원
	private Long pBoardId;
	
	@NotBlank
	private String title;
	
	private String contents;
	
	@NotBlank
	private String author;
	
	@PastOrPresent
	private Date wDate;
	
	private String fPath;
	
}
