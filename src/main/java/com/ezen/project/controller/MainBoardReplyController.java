package com.ezen.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.project.dto.MainBoardReplyDTO;
import com.ezen.project.service.MainBoardReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/pet/replies")
@Slf4j
@RequiredArgsConstructor
public class MainBoardReplyController {

	@Autowired
	MainBoardReplyService svc;
	
	@GetMapping(value = "/mainboard/{mainBoardNum}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MainBoardReplyDTO>> getListByMainBoard(@PathVariable("mainBoardNum") Long mainBoardNum) {
		log.info("mainBoardNum: {}", mainBoardNum);
		return new ResponseEntity<>(svc.getList(mainBoardNum), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Long> register(@RequestBody MainBoardReplyDTO replyDTO) {
		log.info("replyDTO={}", replyDTO);
		
		Long rno = svc.register(replyDTO);
		
		return new ResponseEntity<>(rno, HttpStatus.OK);
	}
	
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		log.info("RNO: {}", rno);
		svc.remove(rno);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@PutMapping("/{rno}")
	public ResponseEntity<String> modify(@RequestBody MainBoardReplyDTO replyDTO) {
		log.info("replyDTO: {}", replyDTO);
		svc.modify(replyDTO);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	
	
}
