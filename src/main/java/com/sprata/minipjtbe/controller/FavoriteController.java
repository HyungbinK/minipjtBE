package com.sprata.minipjtbe.controller;

import com.sprata.minipjtbe.dto.FavoriteDto;
import com.sprata.minipjtbe.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class FavoriteController {
    private final FavoriteService favoriteService;

    @ExceptionHandler(IllegalArgumentException.class)
    public Object nullex(Exception e) {
        return e.getMessage();
    }

    @PostMapping("/api/board/favoriteboard")
    private String pressFavorite(@RequestBody FavoriteDto favoriteDto){
        return favoriteService.pressFavorite(favoriteDto);
    }

    @DeleteMapping("/api/board/favoriteboard")
    private String unpressFavorite(@RequestBody FavoriteDto favoriteDto){
        return favoriteService.unpressFavorite(favoriteDto);
    }
}
