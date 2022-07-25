package com.ezen.project.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="login")
@NoArgsConstructor
@AllArgsConstructor
@Component
public class WalkTUser {

	// ID primary key
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) // JPA에서 자동증가 지원
	private int num;
			
	private String id;
	private String pw;
	
}
