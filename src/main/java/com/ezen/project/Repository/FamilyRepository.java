package com.ezen.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.project.model.Family;

public interface FamilyRepository extends JpaRepository<Family, Long>{

}
