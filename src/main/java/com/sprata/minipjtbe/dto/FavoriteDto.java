package com.sprata.minipjtbe.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Setter
@Getter
@ToString
public class FavoriteDto {
    private Long userId;
    private Long boardId;
}
