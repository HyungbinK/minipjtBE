package com.sprata.minipjtbe.model;

import com.sprata.minipjtbe.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long boardId;

    @Column(nullable = false)
    private String comment;

    public Comment(CommentRequestDto commentRequestDto) {
        this.userId = commentRequestDto.getUserId();
        this.boardId = commentRequestDto.getBoardId();
        this.comment = commentRequestDto.getComment();
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}