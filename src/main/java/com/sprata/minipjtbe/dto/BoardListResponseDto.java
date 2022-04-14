package com.sprata.minipjtbe.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString
public class BoardListResponseDto {
    private int totalPage;
    private List<BoardResponseDto> boardList;

    public BoardListResponseDto(Page<BoardResponseDto> boards) {
        this.totalPage = boards.getTotalPages();
        this.boardList = boards.getContent();
    }
}
