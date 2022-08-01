package com.ezen.project.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
	private Long memberId; //PK
	
	@Email
	@Column(name="member_email")
	private String memberEmail;
	
	@NotBlank
	@Column(name="member_pw")
	private String memberPw;
	
	@NotBlank
	@Column(name="member_name")
	private String memberName;
	
	@NotBlank
	@Column(name="address")
	private String address;
	
	@Column(name="birthday")
	private Date birthday;
	
	@Column(name="gender")
	private String gender;
	
	@NotBlank
	@Column(name="member_phone_number")
	private String memberPhoneNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "familyId")
	private Family familyId;; // FK
}