package com.ezen.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.project.model.ChatChannel;


public interface ChatRepository extends JpaRepository<ChatChannel,Integer>{

	List<ChatChannel> findByUserId(String userId);

	@Transactional
	@Modifying
	@Query(value="UPDATE CHAT_CHANNEL SET CHANNEL_TITLE = ?3 WHERE USER_ID= ?1 AND CHANNEL_CODE=?2",nativeQuery = true)
	void updateTitle(String userId, String reChannelCode, String updateTitle);

	@Transactional
	@Modifying
	@Query(value="DELETE FROM CHAT_CHANNEL WHERE USER_ID = ?1 AND CHANNEL_CODE = ?2",nativeQuery = true)
	int deleteChannel(String userId, String reChannelCode);
	
	@Query(value="SELECT COUNT(USER_ID) FROM CHAT_CHANNEL  WHERE CHANNEL_CODE= ?1 AND USER_ID= ?2",nativeQuery = true)
	Integer existCheck(String chatChannel, String adder);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE CHAT_CHANNEL SET READED=0 WHERE CHAT_NUM=?1",nativeQuery = true)
	int readed(int chatNum);

	List<ChatChannel> findByUserIdOrderByChatNumDesc(String userId);

	//@Query(value="SELECT USER_ID FROM CHAT_CHANNEL WHERE CHANNEL_CODE = ?1",nativeQuery = true)
	//List<String> getMemberList(String channelCode);

}
