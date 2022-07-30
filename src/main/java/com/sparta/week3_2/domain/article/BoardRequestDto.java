package com.sparta.week3_2.domain.article;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardRequestDto {

    private final String author;
    private final String title;
    private final String password;
    private final String content;
}