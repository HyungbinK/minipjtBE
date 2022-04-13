package com.sprata.minipjtbe.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImageRequestDto {
    private Long boardId;
    private MultipartFile file;

    public ImageRequestDto(Long boardId, MultipartFile file) {
        this.boardId = boardId;
        this.file = file;
    }
}
