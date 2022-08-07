package com.ezen.project.model;

import java.sql.Date;
import java.util.List;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"petImages", "mainBoardAuthor"})
@Table(name="main_borad")
public class MainBoard extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="main_board_num", insertable=false, nullable = false)
	private Long mainBoardNum;
	
	@NotBlank
	@Column(name="main_board_title")
	private String mainBoardTitle;
	
	@Column(name="main_board_contents")
	private String mainBoardContents;
	
	@ManyToOne(fetch=FetchType.LAZY) //명시적으로 Lazy 로딩 지정
	@NotNull
	private Member mainBoardAuthor;
	
	@PastOrPresent
	@Column(name="main_board_wdate")
	private Date mainBoardWdate;
	
	@Column(name="main_board_location")
	private String mainBoardLocation;
	
	@OneToMany
	@JoinColumn(name="main_board_num")
	private List<PetImage> petImages;
	
	public void changeTitle(String title) {
		this.mainBoardTitle = title;
	}
	
	public void changeContent(String content) {
		this.mainBoardContents = content;
	}
}
