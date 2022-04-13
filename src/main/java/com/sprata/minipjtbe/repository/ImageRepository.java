package com.sprata.minipjtbe.repository;

import com.sprata.minipjtbe.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
    Image findByBoardId(Long boardId);
}
