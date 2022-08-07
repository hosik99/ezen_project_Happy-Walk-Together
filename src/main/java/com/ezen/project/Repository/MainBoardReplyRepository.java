package com.ezen.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ezen.project.model.MainBoard;
import com.ezen.project.model.MainBoardReply;

public interface MainBoardReplyRepository extends JpaRepository<MainBoardReply, Long>{

	//MainBoard 삭제시에 댓글들 삭제
	@Modifying
	@Query("delete from MainBoardReply r WHERE r.mainboard.mainBoardNum =:num")
	void deleteByMainBoardNum(Long num);
	
	//게시물로 댓글 목록 가져오기
	List<MainBoardReply> getMainBoardRepliesByMainboardOrderByRno(MainBoard mainBoard);
	
}
