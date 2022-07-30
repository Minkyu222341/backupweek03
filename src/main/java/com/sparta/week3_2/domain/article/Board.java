package com.sparta.week3_2.domain.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week3_2.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE)
    private List<Comment> comments;


    public Board(BoardRequestDto boardRequestDto) {
        this.author = boardRequestDto.getAuthor();
        this.password = boardRequestDto.getPassword();
        this.content = boardRequestDto.getContent();
        this.title = boardRequestDto.getTitle();
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.author = boardRequestDto.getAuthor();
        this.password = boardRequestDto.getPassword();
        this.content = boardRequestDto.getContent();
        this.title = boardRequestDto.getTitle();
    }

}