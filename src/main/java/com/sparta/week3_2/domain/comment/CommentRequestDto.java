package com.sparta.week3_2.domain.comment;


import com.sparta.week3_2.domain.article.Board;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentRequestDto {
    private Long id;
    private String username;
    private String text;
    private Board board;
}
