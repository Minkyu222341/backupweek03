package com.sparta.week3_2.domain.article;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime createdAt;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.author = board.getAuthor();
        this.createdAt = board.getCreatedAt();
    }

}
