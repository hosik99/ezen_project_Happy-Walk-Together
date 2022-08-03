package com.ezen.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="member_image")
public class MemberImage {
	
	@Id @GeneratedValue
	private Long memberImageNum;
	
	private String memberImageName;
	
	private String memberImagePath;
}
