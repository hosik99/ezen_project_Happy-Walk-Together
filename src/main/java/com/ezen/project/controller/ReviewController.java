package com.ezen.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.project.dto.ReviewDTO;
import com.ezen.project.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/pet/pictureboard/reviews")
@Slf4j
@RequiredArgsConstructor
public class ReviewController {
	@Autowired
	ReviewService svc;
	
	@GetMapping("/{bno}/all")
	public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("bno") Long bno) {
		log.info("----------------list----------------");
		log.info("bno: {}", bno);
		
		List<ReviewDTO> reviewDTOList = svc.getListOfPictureBoard(bno);
		
		return new ResponseEntity<List<ReviewDTO>>(reviewDTOList, HttpStatus.OK);
	}
	
	@PostMapping("/{bno}")
	public ResponseEntity<Long> addReview(@RequestBody ReviewDTO pictureBoardReviewDTO) {
		log.info("-------------add PictureBoardReview-------------");
		log.info("reviewDTO: {}", pictureBoardReviewDTO);
		
		Long reviewnum = svc.register(pictureBoardReviewDTO);
		
		return new ResponseEntity<Long>(reviewnum, HttpStatus.OK);
	}
	
	@PutMapping("/{bno}/{reviewnum}")
	public ResponseEntity<Long> modifyReview(@PathVariable("bno") Long bno,
											@PathVariable("reviewnum") Long reviewnum,
											@RequestBody ReviewDTO pictureBoardReviewDTO) {
		log.info("-------modify PictureBoardReview------- {}", reviewnum);
		log.info("reviewDTO: " + pictureBoardReviewDTO);
		
		svc.modify(pictureBoardReviewDTO);
		
		return new ResponseEntity<Long>(reviewnum, HttpStatus.OK);
	}
	
	@DeleteMapping("/{bno}/{reviewnum}")
	public ResponseEntity<Long> removeReview(@PathVariable("bno") Long bno,
											@PathVariable("reviewnum") Long reviewnum) {
		log.info("------delete PictureBoardReview------ {}", reviewnum);
		svc.remove(reviewnum);
		return new ResponseEntity<Long>(reviewnum, HttpStatus.OK);
	}
}
