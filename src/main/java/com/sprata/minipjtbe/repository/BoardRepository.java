package com.sprata.minipjtbe.repository;

import com.sprata.minipjtbe.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    Board findBoardById(Long id);
    List<Board> findAllByUserId(Long userId);
    Page<Board> findAll(Pageable pageable);
    Board findBoardByContent(String content);
    int countAllById(Long id);

    @Modifying
    @Query("update Board b set b.views =b.views+1 where b.id=:id")
    int updateViews(Long id);
}
