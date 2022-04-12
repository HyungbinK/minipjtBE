package com.sprata.minipjtbe.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDto {
    private String title;
    private String content;
    private Long userId;
    private String headinfo;
    private String topinfo;
    private String bottominfo;
    private String shoesinfo;
}
