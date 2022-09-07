package com.ezen.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.project.model.Report;

@Transactional
public interface ReportRepository extends JpaRepository<Report,Long>{

	List<Report> findByWriter(String member);

	@Transactional
	@Modifying
	@Query(value="UPDATE REPORTS SET READED = 1 WHERE NUM = ?1",nativeQuery = true)
	void readed(Long num);

}
