package com.ezen.project.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PictureBoardDTO {
	private Long bno;
	private String title;
	
	@Builder.Default
	private List<PictureBoardImageDTO> imageDTOList = new ArrayList<>();
	
	//게시물의 평균 평점
	private double avg;
	
	//평가 수 jpa의 count()
	private int reviewCnt;
	
	private LocalDateTime regDate;
	
	private LocalDateTime modDate;
}
