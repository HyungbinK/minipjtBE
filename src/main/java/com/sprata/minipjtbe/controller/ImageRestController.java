package com.sprata.minipjtbe.controller;

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

    // 사진 삭제
    @DeleteMapping("/api/board/photo/{imageid}")
    public void deleteImage(@PathVariable Long imageid){
        imageService.deleteFile(imageid);
    }

}