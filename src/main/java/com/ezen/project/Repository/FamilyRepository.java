package com.ezen.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.project.model.Family;

public interface FamilyRepository extends JpaRepository<Family, Long>{	
	//findBy 변수이름 을 지정해야 jpa가 찾게 될것이다. 그리고 이거는 찾는 용도다.
		Family findByDelEmail(String delEmail); 
}
