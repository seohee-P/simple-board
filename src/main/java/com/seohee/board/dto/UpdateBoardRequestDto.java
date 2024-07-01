package com.seohee.board.dto;

import lombok.Getter;

@Getter
public class UpdateBoardRequestDto {
    private String title;
    private String content;
    private String username;
    private String password;
}
