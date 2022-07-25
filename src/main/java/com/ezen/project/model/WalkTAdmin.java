package com.ezen.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_admin")
public class WalkTAdmin {
	// ID primary key
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO) // JPA에서 자동증가 지원
		private int num;
		
		private String adminId;
		private String adminPw;
}
