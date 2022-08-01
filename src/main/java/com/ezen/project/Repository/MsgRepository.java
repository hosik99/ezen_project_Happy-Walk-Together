package com.ezen.project.Repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ezen.project.model.Message;

public interface MsgRepository extends JpaRepository<Message,Integer>{

	
	
	
}
