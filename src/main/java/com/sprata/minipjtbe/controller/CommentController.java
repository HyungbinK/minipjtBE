package com.sprata.minipjtbe.controller;

import com.sprata.minipjtbe.dto.CommentRequestDto;
import com.sprata.minipjtbe.dto.CommentResponseDto;
import com.sprata.minipjtbe.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @ExceptionHandler(IllegalArgumentException.class)
    public Object nullex(Exception e) {
        return e.getMessage();
    }
    //댓글 입력
    @PostMapping("/api/comment/regist")
    public String registComment (@RequestBody CommentRequestDto commentRequestDto){
        return commentService.registComment(commentRequestDto);
    }


    //댓글 조회
    @GetMapping("/api/comment/{boardid}")
    public List<CommentResponseDto> getComment(@PathVariable Long boardid){
        return commentService.getComment(boardid);
    }

    //댓글 수정
    @PutMapping("/api/comment/{commentid}")
    public String updateComment(@PathVariable Long commentid, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.update(commentid,commentRequestDto);
    }

    //댓글 삭제
    @DeleteMapping("/api/comment/{commentid}")
    public String deleteComment(@PathVariable Long commentid){
        return  commentService.deleteComment(commentid);
    }
}
