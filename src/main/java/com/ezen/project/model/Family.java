package com.ezen.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="family")
@ToString(exclude= {"pets","members"})
public class Family {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="family_id")
	private Long familyId;
	
	@NotBlank
	@Column(name="family_pwd")
	private String familyPwd;
	
	@OneToMany(mappedBy = "family")
	@Column(name="member")
	private List<Member> members = new ArrayList<>();
	
	public void addMember(Member member) {
		member.setFamily(this);
		getMembers().add(member);
	}
	
	@OneToMany(mappedBy = "family")
	@Column(name="pet")
	private List<Pet> pets = new ArrayList<>();
	
	public void addPet(Pet pet) {
		pet.setFamily(this);
		getPets().add(pet);
	}
	
	// 회원 인증 수단
	@Column(name="del_email", nullable=true)
	private String delEmail;
	
	/*
	@Column(name="del_phone_num")
	private String delPhoneNum; //대표 번호
	
	@Column(name="del_Member, nullable = false")
	private String delegateMember; // 대표 회원
	*/
}
