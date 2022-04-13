package com.sprata.minipjtbe.model;

import com.sprata.minipjtbe.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Board extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private  Long userId;

    @Column(nullable = false)
    private String headinfo;

    @Column(nullable = false)
    private String topinfo;

    @Column(nullable = false)
    private String bottominfo;

    @Column(nullable = false)
    private String shoesinfo;

    @Column(columnDefinition = "integer default 0",nullable = false)
    private int views;

    public Board(BoardRequestDto boardRequestDto){
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.userId = boardRequestDto.getUserId();
        this.headinfo = boardRequestDto.getHeadinfo();
        this.topinfo = boardRequestDto.getTopinfo();
        this.bottominfo = boardRequestDto.getBottominfo();
        this.shoesinfo = boardRequestDto.getShoesinfo();
    }

    public void update(BoardRequestDto boardRequestDto){
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.headinfo = boardRequestDto.getHeadinfo();
        this.topinfo = boardRequestDto.getTopinfo();
        this.bottominfo = boardRequestDto.getBottominfo();
        this.shoesinfo = boardRequestDto.getShoesinfo();
    }

}
