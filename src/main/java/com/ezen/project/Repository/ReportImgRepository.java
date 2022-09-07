package com.ezen.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.project.model.ReportImg;

public interface ReportImgRepository extends JpaRepository<ReportImg,Long>{

	@Transactional
	@Modifying
	@Query(value="SELECT FNAME FROM REPORT_IMGS WHERE PNUM = ?1",nativeQuery = true)
	List<String> findByPnum(Long pnum);

	int deleteByPnum(Long num);
	
	

}
