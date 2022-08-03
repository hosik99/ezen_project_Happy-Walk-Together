package com.ezen.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.project.model.Family;

public interface FamilyRepository extends JpaRepository<Family, Long>{	
	
}
