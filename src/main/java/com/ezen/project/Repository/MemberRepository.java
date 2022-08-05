package com.ezen.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.project.model.Family;
import com.ezen.project.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	@Transactional
	@Modifying
	@Query("UPDATE Member M SET M.family=(SELECT F.familyId FROM Family F WHERE familyId = ?1) WHERE M.memberId = ?2")
	int update_value(Long familyId,Long memberId);	

	List<Member> findByMemberEmail(String memberEmail);
}
