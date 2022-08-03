package com.ezen.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.project.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
