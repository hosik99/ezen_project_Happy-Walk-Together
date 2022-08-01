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

import lombok.Data;

@Component
@Data
@Entity
@Table(name="member_family")
public class PetMeetPage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="meat_page_id", insertable=false, nullable = false)
	private Long meetNum;
	
	@NotBlank
	@Column(name="meet_title")
	private String meetTitle;
	
	@Column(name="meet_contents")
	private String meetContents;
	
	@NotBlank
	@Column(name="meet_author")
	private String meetAuthor;
	
	@PastOrPresent
	@Column(name="meet_wdate")
	private Date meetWdate;
	
	@Column(name="meet_location")
	private String meetLocation;
}
