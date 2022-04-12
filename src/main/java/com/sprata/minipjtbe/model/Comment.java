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

<<<<<<< HEAD
=======
    @Column(nullable = false)
    private Long parentId;




>>>>>>> f520e7f8c0d610aec4c950d049519c06e247f3f1
    public Comment(CommentRequestDto commentRequestDto) {
        this.userId = commentRequestDto.getUserId();
        this.boardId = commentRequestDto.getBoardId();
        this.comment = commentRequestDto.getComment();
        this.parentId= commentRequestDto.getParentId();
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}