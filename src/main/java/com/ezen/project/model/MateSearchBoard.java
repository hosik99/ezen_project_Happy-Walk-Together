package com.ezen.project.model;

import java.sql.Date;

import javax.persistence.Column;
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
@Table(name="mate_search_board")
public class MateSearchBoard {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) // JPA에서 자동증가 지원
	private Long mateBoardNum;
	
	@NotBlank
	@Column(name="mate_title")
	private String mateTitle;
	
	@Column(name="mate_contents")
	private String mateContents;
	
	@NotBlank
	@Column(name="mate_author")
	private String mateAuthor;
	
	@PastOrPresent
	@Column(name="mate_wdate")
	private Date mateWdate;
	
	@Column(name="mate_location")
	private String mateLocation;
	
}
