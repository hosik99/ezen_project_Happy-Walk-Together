package com.ezen.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.project.model.PictureBoardImage;

public interface PictureBoardImageRepository extends JpaRepository<PictureBoardImage, Long> {
}
