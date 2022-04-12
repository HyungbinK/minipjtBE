package com.sprata.minipjtbe.repository;

import com.sprata.minipjtbe.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository <Comment, Long> {
    List<Comment> findAllByBoardId(Long boardId);
    void deleteAllByBoardId(Long boardId);
}
