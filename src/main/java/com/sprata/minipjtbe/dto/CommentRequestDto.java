package com.sprata.minipjtbe.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long userId;
    private Long boardId;
    private String comment;
    private Long parentId;
}
