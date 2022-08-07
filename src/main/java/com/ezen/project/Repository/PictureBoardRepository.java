package com.ezen.project.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ezen.project.model.Movie;
import com.ezen.project.model.PictureBoard;

public interface PictureBoardRepository extends JpaRepository<PictureBoard, Long> {


//    @Query("select m, avg(coalesce(r.grade,0)),  count(r) from Movie m " +
//            "left outer join Review  r on r.movie = m group by m")
//    Page<Object[]> getListPage(Pageable pageable);

//    @Query("select p, pi, avg(coalesce(r.grade,0)),  count(distinct r) from PictureBoard p " +
//            "left outer join PictureBoardImage pi on pi.board = p " +
//            "left outer join Review  r on r.board = p group by p ")
    @Query("select p.bno, min(p.title), pi.inum, pi.uuid, pi.imgName, pi.path, avg(coalesce(r.grade,0)),  count(distinct r) from PictureBoard p " +
            "left outer join PictureBoardImage pi on pi.board = p " +
            "left outer join Review  r on r.board = p group by p ")
    Page<Object[]> getListPage(Pageable pageable);


    @Query("select p, pi ,avg(coalesce(r.grade,0)),  count(r)" +
            " from PictureBoard p left outer join PictureBoardImage pi on pi.board = p " +
            " left outer join Review  r on r.board = p "+
            " where p.bno = :bno group by pi")
    List<Object[]> getPictureBoardWithAll(Long bno);
}
