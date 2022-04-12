package com.sprata.minipjtbe.repository;

import com.sprata.minipjtbe.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
   Optional<Favorite> findByBoardIdAndUserId(Long boardId, Long userId);
   int countAllByBoardId(Long boardId);
   List<Favorite> findAllByUserId(Long userId);
   void deleteAllByBoardId(Long boardId);
}
