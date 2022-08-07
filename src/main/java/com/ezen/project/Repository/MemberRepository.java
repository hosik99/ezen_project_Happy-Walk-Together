package com.ezen.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.project.model.Family;
import com.ezen.project.model.Member;
import com.ezen.project.model.Pet;

public interface MemberRepository extends JpaRepository<Member, Long>{
	@Transactional
	@Modifying
	@Query("UPDATE Member M SET M.family=(SELECT F.familyId FROM Family F WHERE familyId = ?1) WHERE M.memberId = ?2")
	int update_value(Long familyId,Long memberId);	
	
	@Transactional
	@Modifying
	@Query("UPDATE Member m SET"
			+ " m.memberPw=:#{#member.memberPw},"
			+ " m.memberName=:#{#member.memberName},"
			+ " m.memberAge=:#{#member.memberAge},"
			+ " m.birthday=:#{#member.birthday},"
			+ " m.gender=:#{#member.gender},"
			+ " m.memberPhoneNumber=:#{#member.memberPhoneNumber}"
			+ " WHERE m.memberEmail=:#{#member.memberEmail}")
	int update_object(Member member);	

	List<Member> findByMemberEmail(String memberEmail);
	
}
