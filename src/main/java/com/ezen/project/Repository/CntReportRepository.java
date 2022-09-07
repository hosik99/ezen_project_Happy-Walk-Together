package com.ezen.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ezen.project.model.CntReport;


public interface CntReportRepository extends JpaRepository<CntReport,Long>{

	@Query(value="SELECT COUNT(num) FROM CNT_REPORT WHERE MEMBER = ?1 ",nativeQuery = true)
	int cntMemReported(String member);

}
