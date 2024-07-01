package com.seohee.board.dto;

import com.seohee.board.entity.Board;
import lombok.Getter;


@Getter
public class BoardRequestDto {
    private String title;
    private String username;
    private String password;
    private String content;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .username(username)
                .password(password)
                .build();
    }
}
