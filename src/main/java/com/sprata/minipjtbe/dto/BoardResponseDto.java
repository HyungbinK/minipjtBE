package com.sprata.minipjtbe.dto;

import com.sprata.minipjtbe.model.Board;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
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
    private String headinfo;
    private String topinfo;
    private String bottominfo;
    private String shoesinfo;



    public BoardResponseDto() {
    }

    public BoardResponseDto(Board board, int like, UserInfoDto userinfo, boolean mylike, String url, Long imageId){
        this.boardId= board.getId();
        this.title= board.getTitle();
        this.content = board.getContent();
        this.userId = board.getUserId();
        this.bottominfo = board.getBottominfo();
        this.headinfo = board.getHeadinfo();
        this.shoesinfo = board.getShoesinfo();
        this.topinfo = board.getTopinfo();
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
