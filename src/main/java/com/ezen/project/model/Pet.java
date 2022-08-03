package com.ezen.project.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="pet")
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="pet_id")
	private Long petId;
	
	@NotBlank
	@Column(name="pet_name")
	private String petName; 
	
	@Column(name="breed")
	private String breed; //품종 이름
	
	@Column(name="pet_size")
	private String petSize; //사이즈(대형견 중형견 소형견)
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="family_id")
	private Family family;
}
