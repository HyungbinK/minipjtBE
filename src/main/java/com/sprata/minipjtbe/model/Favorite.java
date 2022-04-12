package com.sprata.minipjtbe.model;

import com.sprata.minipjtbe.dto.FavoriteDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Favorite {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long boardId;

    public Favorite(FavoriteDto favoriteDto){
        this.userId = favoriteDto.getUserId();
        this.boardId= favoriteDto.getBoardId();
    }
}
