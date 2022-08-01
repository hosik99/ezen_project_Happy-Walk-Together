package com.ezen.project.model;

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
@Table(name="member")
public class Member {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="member_id", insertable=false, nullable = false)
	private Long memberId;
	
	private String memberEmail;
	
	private String memberPw;
	
	private String location;
	
	private String memberName;
	
	private String birthday;
	
	private String gender;
	
	private String phoneNumber;
	
	private Family familyId;
}
