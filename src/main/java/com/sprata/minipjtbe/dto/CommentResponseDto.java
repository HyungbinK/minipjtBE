package com.sprata.minipjtbe.dto;

import com.sprata.minipjtbe.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private Long userId;
    private UserInfoDto userInfo;
    private Long boardId;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long parentId;
//    private List<Recomment> recommentList;


    public CommentResponseDto(Comment comment, UserInfoDto userInfo) {
        this.id = comment.getId();
        this.userId = comment.getUserId();
        this.userInfo = userInfo;
        this.boardId = comment.getBoardId();
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.parentId = comment.getParentId();

    }

}
