package com.ezen.project.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ezen.project.Repository.search.SearchMainBoardRepository;
import com.ezen.project.model.MainBoard;

public interface MainBoardRepository extends JpaRepository<MainBoard, Long>, SearchMainBoardRepository {
	
	//한개의 로우(Object) 내에 Object[ ]로 나옴
	//Object result = mainBoardRepository.getBoardWithAuthor(100L);
	//Object[] arr = (Object[]) result;
	//Arrays.toString(arr); 로 사용
	@Query("select mb, a from MainBoard mb left join mb.mainBoardAuthor a where mb.mainBoardNum =:num")
	Object getMainBoardWithAuthor(@Param("num") Long num);
	
	//연관관계가 없는 엔티티 조인 처리에는 on (MainBoard는 Reply 참조 안함)
	//List<Object[]> result = mainBoardRepository.getBoardWithReply(100L);
	//for(Object[] arr : result) {
	//	System.out.println(Arrays.toString(arr));
	//} 로 사용
	@Query("SELECT mb, r FROM MainBoard mb LEFT JOIN MainBoardReply r ON r.mainboard = mb WHERE mb.mainBoardNum =:num")
	List<Object[]> getMainBoardWithReply(@Param("num") Long num);
	
//	@Query(value="SELECT mb, a, count(r) " +
//			" FROM MainBoard mb " +
//			" LEFT JOIN mb.mainBoardAuthor a " +
//			" LEFT JOIN MainBoardReply r ON r.mainboard = mb " +
//			" GROUP BY mb",
//			countQuery = "SELECT count(mb) FROM MainBoard mb")
	@Query(value ="SELECT mb.mainBoardNum , min(mb.mainBoardTitle) , min(a.memberEmail), count(r) " +
			" FROM MainBoard mb " +
			" LEFT JOIN mb.mainBoardAuthor a " +
			" LEFT JOIN MainBoardReply r ON r.mainboard = mb " +
			" GROUP BY mb",
			countQuery ="SELECT count(mb) FROM MainBoard mb")
	Page<Object[]> getMainBoardWithReplyCount(Pageable pageable); // 목록 화면에 필요한 데이터
	
//	@Query(value="SELECT mb, a, count(r) " +
//			" FROM MainBoard mb " +
//			" LEFT JOIN mb.mainBoardAuthor a " +
//			" LEFT JOIN MainBoardReply r ON r.mainboard = mb " +
//			" WHERE mb.mainBoardNum =:num")
    @Query(value="SELECT mb.mainBoardNum, min(mb.mainBoardTitle), min(a.memberEmail), count(r) " +
            " FROM MainBoard mb LEFT JOIN mb.mainBoardAuthor a " +
            " LEFT OUTER JOIN MainBoardReply r ON r.mainboard = mb " +
            " Group by mb " +
            " having mb.mainBoardNum = :num",
            countQuery ="SELECT count(mb) FROM MainBoard mb")
	Object getMainBoardByNum(@Param("num") Long num);
			
	
}
