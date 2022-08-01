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
@Table(name="member_family")
public class Family {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="family_id", insertable=false, nullable = false)
	private Long familyId;
	
	@Column(name="member")
	private WalkTUser walkTUser;
	
	@Column(name="pet")
	private Pet pet;
	
	@Column(name="del_phone_num, nullable")
	private String delegatePhoneNumber; //대표 번호
	
	@Column(name="del_Member, nullable = false")
	private String delegateMember; // 대표 회원
}
