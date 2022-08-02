package com.ezen.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Entity
@Table(name="pet")
public class Pet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	// IDENTITY 안될경우 AUTO나 SEQ로 바꿔서 사용하세요
	@Column(name="pet_id", insertable=false, nullable = false)
	private Long petId;
	
	@NotBlank
	@Column(name="pet_name")
	private String petName; 
	
	@Column(name="breed")
	private String breed; //품종 이름
	
	@Column(name="pet_size")
	private String petSize; //사이즈(대형견 중형견 소형견)
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="family_id", referencedColumnName="familyId")
	private Family family;
}