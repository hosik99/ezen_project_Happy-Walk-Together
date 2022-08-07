package com.ezen.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.project.Repository.MainBoardReplyRepository;
import com.ezen.project.dto.MainBoardReplyDTO;
import com.ezen.project.model.MainBoard;
import com.ezen.project.model.MainBoardReply;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainBoardReplyService {
	
	@Autowired
	MainBoardReplyRepository mainBoardReplyRepository;
	
	// MainBoardReplyDTO를 MainBoardReply객체로 변환
	// MainBoard객체의 처리가 수반됨
	public MainBoardReply dtoToEntity(MainBoardReplyDTO dto) {
		MainBoard mainBoard = MainBoard.builder().mainBoardNum(dto.getMainBoardNum()).build();
		
		MainBoardReply mainBoardReply = MainBoardReply.builder()
										.rno(dto.getRno())
										.text(dto.getText())
										.replyer(dto.getReplyer())
										.mainboard(mainBoard)
										.build();
		
		return mainBoardReply;
	}
	
	//MainBoardReply객체를 MainBoardReplyDTO로 변환
	//MainBoard 객체가 필요하지 않으므로 게시물 번호만
	public MainBoardReplyDTO entityToDTO(MainBoardReply reply) {
		MainBoardReplyDTO dto = MainBoardReplyDTO.builder()
								.rno(reply.getRno())
								.text(reply.getText())
								.replyer(reply.getReplyer())
								.regDate(reply.getRegDate())
								.modDate(reply.getModDate())
								.build();
		
		return dto;
	}
	
	public Long register(MainBoardReplyDTO replyDTO) {
		MainBoardReply reply = dtoToEntity(replyDTO);
		
		mainBoardReplyRepository.save(reply);
		
		return reply.getRno();
	}
	
	public List<MainBoardReplyDTO> getList(Long num) {
		List<MainBoardReply> result = mainBoardReplyRepository
							.getMainBoardRepliesByMainboardOrderByRno(MainBoard.builder().mainBoardNum(num).build());
		
		return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
	}
	
	public void modify(MainBoardReplyDTO replyDTO) {
		MainBoardReply reply = dtoToEntity(replyDTO);
		mainBoardReplyRepository.save(reply);
	}
	
	public void remove(Long rno) {
		mainBoardReplyRepository.deleteById(rno);
	}
	
	
}
