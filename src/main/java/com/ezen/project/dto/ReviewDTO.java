package com.ezen.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

	//review num
	private Long reviewnum;
	
	//PictureBoard bno
	private Long bno;
	
	//Member id
	private Long memberId;
	//Member nickname
	private String memberName;
	//Member email;
	private String memberEmail;
	
	private int grade;
	
	private String text;
	
	private LocalDateTime regDate, modDate;
	
}
