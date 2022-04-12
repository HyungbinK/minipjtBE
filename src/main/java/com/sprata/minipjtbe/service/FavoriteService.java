package com.sprata.minipjtbe.service;

import com.sprata.minipjtbe.dto.FavoriteDto;
import com.sprata.minipjtbe.model.Favorite;
import com.sprata.minipjtbe.repository.BoardRepository;
import com.sprata.minipjtbe.repository.FavoriteRepository;
import com.sprata.minipjtbe.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final Validator validator;

    //좋아요
    public String pressFavorite(FavoriteDto favoriteDto){
        Favorite favorite = new Favorite(favoriteDto);
        validator.alreadyDelete(favoriteRepository.findByBoardIdAndUserId(favoriteDto.getBoardId(), favoriteDto.getUserId()).isPresent(), "좋아요가 이미 눌러진 상태입니다.");
        favoriteRepository.save(favorite);
        return "좋아요";
    }

    //좋아요 취소
    public String unpressFavorite(FavoriteDto favoriteDto){
        Long userId = favoriteDto.getUserId();
        Long boardId = favoriteDto.getBoardId();
        Optional<Favorite> favorite = favoriteRepository.findByBoardIdAndUserId(boardId,userId);
        validator.alreadyDelete(!favorite.isPresent(), "이미 좋아요가 취소된 상태입니다.");
        favoriteRepository.deleteById(favorite.get().getId());
        return "좋아요";
    }

}
