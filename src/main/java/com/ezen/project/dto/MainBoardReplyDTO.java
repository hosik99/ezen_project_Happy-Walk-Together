package com.ezen.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MainBoardReplyDTO {
	private Long rno;
	
	private String text;
	
	private String replyer;
	
	private Long mainBoardNum;
	
	private LocalDateTime regDate, modDate;
}
