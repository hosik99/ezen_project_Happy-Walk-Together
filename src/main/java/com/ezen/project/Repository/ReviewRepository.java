package com.ezen.project.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ezen.project.model.Member;
import com.ezen.project.model.PictureBoard;
import com.ezen.project.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByBoard(PictureBoard board);


    @Modifying
    @Query("delete from Review pr where pr.member = :member")
    void deleteByMember(Member member);

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    Page<Review> findAll(Pageable pageable);

}
