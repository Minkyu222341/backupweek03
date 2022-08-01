package com.sparta.week3_2.domain.article;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private String username;

    public BoardResponseDto(Board board,String username) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.createdAt = board.getCreatedAt();
        this.username = username;
    }

}
