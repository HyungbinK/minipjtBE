package com.sprata.minipjtbe.controller;

import com.sprata.minipjtbe.dto.BoardRequestDto;
import com.sprata.minipjtbe.dto.BoardResponseDto;
import com.sprata.minipjtbe.security.UserDetailsImpl;
import com.sprata.minipjtbe.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    //게시글 작성하기
    @PostMapping("/api/board/regist")
    public String registBoard(@RequestParam("files") MultipartFile file, @RequestParam("title") String title,
                              @RequestParam("content") String content, @RequestParam("userId") Long userId,
                              @RequestParam("headinfo") String headinfo, @RequestParam("topinfo") String topinfo,
                              @RequestParam("bottominfo") String bottominfo, @RequestParam("shoesinfo") String shoesinfo) throws IOException {
        BoardRequestDto boardDto = new BoardRequestDto(title, content, userId, headinfo, topinfo,  bottominfo, shoesinfo);
        boardService.registBoard(boardDto, file);
        return "gogo";
    }

    //전체 게시글 조회
    @GetMapping("/api/board/{userid}/{page}")
    public Page<BoardResponseDto> showAllBoard(@PathVariable int page,
                                               @PathVariable Long userid){
        return boardService.showAllBoard(page,userid);
    }

    //게시글 수정
    @PutMapping("/api/board/{boardid}")
    public String updateBoard(@PathVariable Long boardid, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.updateBoard(boardid, boardRequestDto);
    }

    //게시글 삭제
    @DeleteMapping("/api/board/{boardid}")
    public String deleteBoard(@PathVariable Long boardid){
        return boardService.deleteBoard(boardid);
    }

    //내가 작성한 게시글 조회
    @GetMapping("/api/board/myboard/{userid}/{page}")
    public Page<BoardResponseDto> showMyBoard(@PathVariable Long userid,
                                              @PathVariable int page){
        return boardService.showMyBoard(userid,page);
    }

    //내가 좋아요한 게시글 조회
    @GetMapping("/api/board/favoriteboard/{userid}/{page}")
    public Page<BoardResponseDto> showFavoriteBoard(@PathVariable Long userid,
                                                    @PathVariable int page){
        return boardService.showFavoriteBoard(userid,page);
    }


    //조회수
    @PutMapping("/api/board/views/{boardid}")
    public String updateViews(@PathVariable Long boardid){
        return boardService.updateViews(boardid);
    }

    //상세페이지
    @GetMapping("/api/board/detail/{boardid}")
    public BoardResponseDto showBoardDetail(@PathVariable Long boardid,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        return boardService.showBoardDetail(boardid,userId);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Object nullex(Exception e) {
        return e.getMessage();
    }

}
