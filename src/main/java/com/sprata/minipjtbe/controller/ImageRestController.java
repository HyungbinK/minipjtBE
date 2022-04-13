package com.sprata.minipjtbe.controller;

import com.sprata.minipjtbe.dto.BoardDto;
import com.sprata.minipjtbe.dto.ImageRequestDto;
import com.sprata.minipjtbe.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageRestController {
    private final ImageService imageService;

    @Autowired
    public ImageRestController(ImageService imageService){
        this.imageService = imageService;
    }

    // 사진 등록
    @PostMapping("/api/board/photo")
    public void upload(@RequestParam("files") MultipartFile file, @RequestParam("boardId") Long id) throws IOException {
        System.out.println(file);
        System.out.println(id);
//        imageService.upload(imageRequestDto, "static");
    }

    // 사진 삭제
    @DeleteMapping("/api/board/photo/{imageid}")
    public void deleteImage(@PathVariable Long imageid){
        imageService.deleteFile(imageid);
    }

}