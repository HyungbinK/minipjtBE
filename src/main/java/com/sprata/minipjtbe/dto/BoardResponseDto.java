package com.sprata.minipjtbe.dto;

import com.sprata.minipjtbe.model.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BoardResponseDto {
    private Long boardId;
    private Long userId;
    private String title;
    private String content;
    private int like;
    private UserInfoDto userinfo;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int views;
    private boolean mylike;
    private String url;
    private Long imageId;

    public BoardResponseDto(Board board, int like, UserInfoDto userinfo, boolean mylike, String url, Long imageId){
        this.boardId= board.getId();
        this.title= board.getTitle();
        this.content = board.getContent();
        this.userId = board.getUserId();
        this.like = like;
        this.userinfo = userinfo;
        this.createdAt =LocalDateTime.of(board.getCreatedAt().getYear(), board.getCreatedAt().getMonth(),
                board.getCreatedAt().getDayOfMonth(), board.getCreatedAt().getHour(), board.getCreatedAt().getMinute(), 0);
        this.modifiedAt =LocalDateTime.of(board.getModifiedAt().getYear(), board.getModifiedAt().getMonth(),
                board.getModifiedAt().getDayOfMonth(), board.getModifiedAt().getHour(), board.getModifiedAt().getMinute(), 0);
        this.views = board.getViews();
        this.mylike = mylike;
        this.url = url;
        this.imageId = imageId;
    }
}
