package com.sparta.week3_2.domain.comment;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.week3_2.domain.article.Board;
import com.sparta.week3_2.domain.article.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonBackReference
    private Board board;


    public Comment(CommentRequestDto commentRequestDto,Board board) {
        this.text = commentRequestDto.getText();
        this.username = commentRequestDto.getUsername();
        this.board = board;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.text = commentRequestDto.getText();
        this.username = commentRequestDto.getUsername();
    }
}
