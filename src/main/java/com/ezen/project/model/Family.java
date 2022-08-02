package com.ezen.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Component
@Data
@Entity
@Table(name="member_family")
@ToString(exclude ={"pet", "member"})
public class Family {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="family_id", insertable=false, nullable = false)
	private Long familyId;
	
	@NotBlank
	@Column(name="family_pwd")
	private String familyPwd;
	
	@OneToMany(mappedBy="family")
	@Column(name="member")
	private List<Member> member;
	
	@OneToMany(mappedBy="family")
	@Column(name="pet")
	private List<Pet> pet;
	
	// 회원 인증 수단
	@Column(name="del_email, nullable")
	private String delEmail;
	
	/*
	@Column(name="del_phone_num")
	private String delPhoneNum; //대표 번호
	
	@Column(name="del_Member, nullable = false")
	private String delegateMember; // 대표 회원
	*/
}
