package com.ezen.project.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Entity
@Table(name="chatChannel")
public class ChatChannel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long chatNum;
	
	@NotEmpty
	private String userId;
	
	@NotEmpty
	private String channelCode;
	
	private Date createDate;
	
	private String channelTitle="<새로 초대받은 채팅방 입니다.>";
	
	private Integer readed=1;
}
