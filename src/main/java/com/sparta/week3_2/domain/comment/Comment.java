package com.sparta.week3_2.domain.comment;


import com.sparta.week3_2.domain.article.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long comment_id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "id")
    private Board board;


    public Comment(CommentRequestDto commentRequestDto,Board board) {
        this.text = commentRequestDto.getText();
        this.username = commentRequestDto.getUsername();
        this.board = board;
    }
}
