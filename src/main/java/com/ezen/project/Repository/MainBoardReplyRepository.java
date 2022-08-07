package com.ezen.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ezen.project.model.MainBoardReply;

public interface MainBoardReplyRepository extends JpaRepository<MainBoardReply, Long>{

	@Modifying
	@Query("delete from MainBoardReply r WHERE r.mainboard.mainBoardNum = :num")
	void deleteByMainBoardNum(Long num);
}
