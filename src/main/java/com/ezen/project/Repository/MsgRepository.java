package com.ezen.project.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.project.model.Message;

public interface MsgRepository extends JpaRepository<Message,Integer>{

	
	
	
}
