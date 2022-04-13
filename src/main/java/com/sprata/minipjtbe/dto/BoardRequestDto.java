package com.sprata.minipjtbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardRequestDto {
    private String title;
    private String content;
    private Long userId;
    private String headinfo;
    private String topinfo;
    private String bottominfo;
    private String shoesinfo;

    public BoardRequestDto() {
    }

    public BoardRequestDto(String title, String content, Long userId, String headinfo, String topinfo, String bottominfo, String shoesinfo) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.headinfo = headinfo;
        this.topinfo = topinfo;
        this.bottominfo = bottominfo;
        this.shoesinfo = shoesinfo;
    }
}
