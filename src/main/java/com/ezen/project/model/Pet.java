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
@Table(name="pet")
public class Pet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	// IDENTITY 안될경우 AUTO나 SEQ로 바꿔서 사용하세요
	@Column(name="pet_id", insertable=false, nullable = false)
	private Long petId;
	private String petName; 
	private String breed; //품종 이름
	private String size; //사이즈(대형견 중형견 소형견)
	
	private Family familyId;
}