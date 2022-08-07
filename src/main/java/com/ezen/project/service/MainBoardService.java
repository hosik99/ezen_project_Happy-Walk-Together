package com.ezen.project.service;

import java.util.function.Function;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ezen.project.Repository.MainBoardReplyRepository;
import com.ezen.project.Repository.MainBoardRepository;
import com.ezen.project.Repository.MemberRepository;
import com.ezen.project.dto.MainBoardDTO;
import com.ezen.project.dto.PageRequestDTO;
import com.ezen.project.dto.PageResultDTO;
import com.ezen.project.model.MainBoard;
import com.ezen.project.model.Member;
import com.querydsl.core.BooleanBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainBoardService {
	@Autowired
	MainBoardRepository mainBoardRepository;
	
	@Autowired
	MainBoardReplyRepository mainBoardReplyRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	public MainBoard dtoToEntity(MainBoardDTO dto) {
		Member member = memberRepository.findByMemberEmail(dto.getWriterEmail()).get(0);
//				Member.builder()
//						.memberEmail(dto.getWriterEmail()).build();
		
		MainBoard mainBoard = MainBoard.builder()
								.mainBoardNum(dto.getMainBoardNum())
								.mainBoardTitle(dto.getTitle())
								.mainBoardContents(dto.getContent())
								.mainBoardAuthor(member)
								.build();
		return mainBoard;
	}
	
	public MainBoardDTO entityToDTO(MainBoard mainBoard, Member member, Long replyCount) {
		MainBoardDTO mainBoardDTO = MainBoardDTO.builder()
										.mainBoardNum(mainBoard.getMainBoardNum())
										.title(mainBoard.getMainBoardTitle())
										.content(mainBoard.getMainBoardContents())
										.regDate(mainBoard.getRegDate())
										.modDate(mainBoard.getModDate())
										.writerEmail(member.getMemberEmail())
										.writerName(member.getMemberName())
										.replyCount(replyCount.intValue()) //long으로 나오므로 int로 처리하도록
										.build();
		return mainBoardDTO;
	}
	
	public Long register(MainBoardDTO dto) {
		log.info("dto={}", dto);
		MainBoard mainBoard = dtoToEntity(dto);
		mainBoardRepository.save(mainBoard);
		return mainBoard.getMainBoardNum();
	}
	
	public PageResultDTO<MainBoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		log.info("pageRequestDTO={}", pageRequestDTO);
		
		Function<Object[], MainBoardDTO> fn = (en -> entityToDTO(mainBoardRepository.findById((Long)en[0]).get(), memberRepository.findByMemberEmail((String)en[2]).get(0), (Long)en[3]));
		
		Page<Object[]> result = mainBoardRepository.getMainBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("mainBoardNum").descending()));
//		Page<Object[]> result = mainBoardRepository.searchPage(
//								pageRequestDTO.getType(),
//								pageRequestDTO.getKeyword(),
//								pageRequestDTO.getPageable(Sort.by("mainBoardNum").descending()));
		
		return new PageResultDTO<>(result, fn);
	}
	
	public MainBoardDTO get(Long num) {
		Object result = mainBoardRepository.getMainBoardByNum(num);
		Object[] arr = (Object[]) result;
//		return entityToDTO((MainBoard)arr[0], (Member)arr[1], (Long)arr[2]);
		return entityToDTO(mainBoardRepository.findById((Long)arr[0]).get(), (Member)memberRepository.findByMemberEmail((String)arr[2]).get(0), (Long)arr[3]);
	}
	
	@Transactional
	public void removeWithReplies(Long num) {
		//댓글부터 삭제
		mainBoardReplyRepository.deleteByMainBoardNum(num);
		
		mainBoardRepository.deleteById(num);
	}
	
	@Transactional
	public void modify(MainBoardDTO dto) {
		MainBoard mainBoard = mainBoardRepository.findById(dto.getMainBoardNum()).get();
		
		mainBoard.changeTitle(dto.getTitle());
		mainBoard.changeContent(dto.getContent());
		
		mainBoardRepository.save(mainBoard);
	}
	
//	private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
//		String type = requestDTO.getType();
//		
//		BooleanBuilder booleanBuilder = new BooleanBuilder();
//		
//		
//	}
}
