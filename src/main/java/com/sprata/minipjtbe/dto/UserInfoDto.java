package com.sprata.minipjtbe.dto;

import com.sprata.minipjtbe.security.UserDetailsImpl;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserInfoDto {
    private Long userId;
    private String username;
    private String nickname;

    public UserInfoDto(UserDetailsImpl userDetails){
        this.userId = userDetails.getId();
        this.username = userDetails.getUsername();
        this.nickname = userDetails.getNickname();
    }

    public UserInfoDto(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }
}
