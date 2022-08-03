package com.ezen.project.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Component
@Data
@Entity
@Table(name="main_borad")
@ToString(exclude = "petImages")
public class MainBoard {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="main_board_num", insertable=false, nullable = false)
	private Long mainBoardNum;
	
	@NotBlank
	@Column(name="main_board_title")
	private String mainBoardNumTitle;
	
	@Column(name="main_board_contents")
	private String mainBoardContents;
	
	@NotBlank
	@Column(name="main_board_author")
	private String mainBoardAuthor;
	
	@PastOrPresent
	@Column(name="main_board_wdate")
	private Date mainBoardWdate;
	
	@Column(name="main_board_location")
	private String mainBoardLocation;
	
	@OneToMany
	@JoinColumn(name="main_board_num")
	private List<PetImage> petImages;
}
