package com.sparta.week3_2.controller;

import com.sparta.week3_2.domain.article.Board;
import com.sparta.week3_2.domain.article.BoardRequestDto;
import com.sparta.week3_2.domain.article.BoardResponseDto;
import com.sparta.week3_2.service.BoardService;
import com.sparta.week3_2.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {


    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/board")
        public List<BoardResponseDto> getList() {
        return boardService.getBoardList();
    }

    @GetMapping("/board/detail/{id}")
    public Board detail(@PathVariable Long id) {
        return boardService.detailPost(id);
    }

    @GetMapping("/auth/board/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        log.info("업데이트폼={}",id);
        Board board = boardService.detailPost(id);
        return board+"번 수정완료";
    }

    @PostMapping("/auth/board")
    public String addPost(@RequestBody BoardRequestDto boardRequestDto) {
        log.info("Post요청={}","ADDPOST 실행");
        System.out.println(boardRequestDto.getContent()+"값 테스트");
        Board board = boardService.savePost(boardRequestDto);

        return "ok";
    }

    @PutMapping("/auth/board/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.update(id, boardRequestDto);
    }

    @DeleteMapping("/auth/board/{id}")
    public String delPost(@PathVariable Long id) {
        log.info("삭제호출={}",id);
        return boardService.delete(id);
    }

//    @PostMapping("/board/{id}")
//    public Boolean validPassword(@PathVariable Long id, @RequestBody BoardValidDto boardValidDto) {
//        return boardService.validPwd(id, boardValidDto);
//    }

    @GetMapping("/add")
    public String addPost() {
        return "addPost";
    }

}

