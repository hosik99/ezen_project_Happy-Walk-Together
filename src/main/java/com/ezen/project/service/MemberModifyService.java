package com.ezen.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.project.Repository.MemberRepository;
import com.ezen.project.model.Member;
import com.ezen.project.model.Pet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberModifyService {

	@Autowired
	private MemberRepository memberRepository;
	
	//검색
		public List<Member> findByMemberEmail(String memberEmail) {
			List<Member> op =memberRepository.findByMemberEmail(memberEmail);
			return op;	
		}
		
		//수정
		public boolean update(Member member) {
			List<Member> list = memberRepository.findAll();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getMemberEmail().equals(member.getMemberEmail())) {
					System.out.println(" member 입장");
					int savedUser = memberRepository.update_object(member);
					log.trace("seavice update : {}",savedUser);
					return true;
				}else{}		
			}
			return false;
		}
	
	
}
