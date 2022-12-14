package com.sparta.week3_2.domain.article;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardRequestDto {
    private String author;
    private String title;
    private String password;
    private String content;
}