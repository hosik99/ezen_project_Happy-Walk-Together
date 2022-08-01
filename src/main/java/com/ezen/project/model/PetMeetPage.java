package com.ezen.project.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	private String meetTitle;
	private String meetContents;
	private String meetAuthor;

	private Date meetWdate;
	private String meetLocation;
}
