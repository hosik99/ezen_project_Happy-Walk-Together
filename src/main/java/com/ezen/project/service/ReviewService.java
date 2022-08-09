package com.ezen.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.project.Repository.ReviewRepository;
import com.ezen.project.dto.ReviewDTO;
import com.ezen.project.model.Member;
import com.ezen.project.model.PictureBoard;
import com.ezen.project.model.Review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;

	public Review dtoToEntity(ReviewDTO reviewDTO) {
		
		Review pictureBoardReview = Review.builder()
								.reviewnum(reviewDTO.getReviewnum())
								.board(PictureBoard.builder().bno(reviewDTO.getBno()).build())
								.member(Member.builder().memberId(reviewDTO.getMemberId()).build())
								.grade(reviewDTO.getGrade())
								.text(reviewDTO.getText())
								.build();
		
		return pictureBoardReview;
	}
	
	public ReviewDTO entityToDto(Review pictureBoardReview) {
		
		ReviewDTO pictureBoardReviewDTO = ReviewDTO.builder()
										.reviewnum(pictureBoardReview.getReviewnum())
										.bno(pictureBoardReview.getBoard().getBno())
										.memberId(pictureBoardReview.getMember().getMemberId())
										.memberName(pictureBoardReview.getMember().getMemberName())
										.memberEmail(pictureBoardReview.getMember().getMemberEmail())
										.grade(pictureBoardReview.getGrade())
										.text(pictureBoardReview.getText())
										.regDate(pictureBoardReview.getRegDate())
										.modDate(pictureBoardReview.getModDate())
										.build();
		
		return pictureBoardReviewDTO;
	}
	
	public List<ReviewDTO> getListOfPictureBoard(Long bno) {
		PictureBoard pictureBoard = PictureBoard.builder().bno(bno).build();
		
		List<Review> result = reviewRepository.findByBoard(pictureBoard);
		
		return result.stream().map(pictureBoardReview -> entityToDto(pictureBoardReview)).collect(Collectors.toList());
	}
	
	public Long register(ReviewDTO pictureBoardReviewDTO) {
		Review pictureBoardReview = dtoToEntity(pictureBoardReviewDTO);
		
		reviewRepository.save(pictureBoardReview);
		
		return pictureBoardReview.getReviewnum();
	}
	
	public void modify(ReviewDTO pictureBoardReviewDTO) {
		Optional<Review> result = reviewRepository.findById(pictureBoardReviewDTO.getReviewnum());
		
		if(result.isPresent()) {
			Review pictureBoardReview = result.get();
			pictureBoardReview.changeGrade(pictureBoardReviewDTO.getGrade());
			pictureBoardReview.changeText(pictureBoardReviewDTO.getText());
			
			reviewRepository.save(pictureBoardReview);
		}
	}
	
	public void remove(Long reviewnum) {
		reviewRepository.deleteById(reviewnum);
	}
}
