package com.ezen.project.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.project.model.Family;


@Repository
public interface SignUpRepository extends JpaRepository<Family, Integer> {
	
	
}
