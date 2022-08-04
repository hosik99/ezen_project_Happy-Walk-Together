package com.ezen.project.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.project.model.Family;
import com.ezen.project.model.Member;


@Repository
public interface SignUpRepository extends JpaRepository<Family, Integer> {
	//findBy 변수이름 을 지정해야 jpa가 찾게 될것이다. 그리고 이거는 찾는 용도다.
	Family findByDelEmail(String delEmail);
	
	 
}
