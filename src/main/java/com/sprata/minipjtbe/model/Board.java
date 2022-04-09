package com.sprata.minipjtbe.model;

import com.sprata.minipjtbe.dto.BoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Board extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Board(BoardDto boardDto){
        this.title =boardDto.getTitle();
        this.content = boardDto.getContent();
        this.userId = boardDto.getUserId();
        this.headinfo = boardDto.getHeadinfo();
        this.topinfo = boardDto.getTopinfo();
        this.bottominfo = boardDto.getBottominfo();
        this.shoesinfo = boardDto.getShoesinfo();
    }

    public void update(BoardDto boardDto){
        this.title =boardDto.getTitle();
        this.content = boardDto.getContent();
        this.headinfo = boardDto.getHeadinfo();
        this.topinfo = boardDto.getTopinfo();
        this.bottominfo = boardDto.getBottominfo();
        this.shoesinfo = boardDto.getShoesinfo();
    }

}