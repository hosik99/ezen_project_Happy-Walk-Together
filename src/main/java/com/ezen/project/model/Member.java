package com.ezen.project.model;

import java.sql.Date;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MEMBER_ID")
	private Long memberId; // PK

	@Email
	@Column(name = "member_email")
	private String memberEmail;

	@NotBlank
	@Column(name = "member_pw")
	private String memberPw;

	@NotBlank
	@Column(name = "member_name")
	private String memberName;

	@NotBlank
	@Column(name = "address")
	private String address;

	@Column(name = "birthday")
	private Date birthday;

	@Min(value = 0)
	@Column(name = "member_age")
	private Integer memberAge;

	@Column(name = "gender")
	private String gender;

	@NotBlank
	@Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "번호 양식과 맞지 않습니다. 01x-(x)xxx-xxxx 형식으로 입력해주세요.")
	@Column(name = "member_phone_number")
	private String memberPhoneNumber;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="family_id")
	private Family family; // FK
}
