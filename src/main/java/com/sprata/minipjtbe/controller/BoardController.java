package com.sprata.minipjtbe.controller;

import com.sprata.minipjtbe.dto.BoardDto;
import com.sprata.minipjtbe.dto.BoardsDto;
import com.sprata.minipjtbe.security.UserDetailsImpl;
import com.sprata.minipjtbe.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //게시글 작성하기
    @PostMapping("/api/board/regist")
    public String registBoard(@RequestBody BoardDto boardDto){
        return boardService.registBoard(boardDto);
    }

    //전체 게시글 조회
    @GetMapping("/api/board/{page}")
    public Page<BoardsDto> showAllBoard(@PathVariable int page,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        return boardService.showAllBoard(page,userId);
    }

    //게시글 수정
    @PutMapping("/api/board/{boardid}")
    public String updateBoard(@PathVariable Long boardid, @RequestBody BoardDto boardDto) {
        return boardService.updateBoard(boardid, boardDto);
    }

    //게시글 삭제
    @DeleteMapping("/api/board/{boardid}")
    public String deleteBoard(@PathVariable Long boardid){
        return boardService.deleteBoard(boardid);
    }

    //내가 작성한 게시글 조회
    @GetMapping("/api/board/myboard/{userid}/{page}")
    public Page<BoardsDto> showMyBoard(@PathVariable Long userid,
                                       @PathVariable int page){
        return boardService.showMyBoard(userid,page);
    }

    //내가 좋아요한 게시글 조회
    @GetMapping("/api/board/favoriteboard/{userid}/{page}")
    public Page<BoardsDto> showFavoriteBoard(@PathVariable Long userid,
                                             @PathVariable int page){
        return boardService.showFavoriteBoard(userid,page);
    }

    //상세페이지
    @GetMapping("/api/board/detail/{boardid}")
    public BoardsDto showBoardDetail(@PathVariable Long boardid,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        return boardService.showBoardDetail(boardid,userId);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Object nullex(Exception e) {
        return e.getMessage();
    }

}
