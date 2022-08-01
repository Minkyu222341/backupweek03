package com.sparta.week3_2.controller;

import com.sparta.week3_2.domain.article.Board;
import com.sparta.week3_2.domain.article.BoardRequestDto;
import com.sparta.week3_2.domain.article.BoardResponseDto;
import com.sparta.week3_2.domain.article.BoardValidDto;
import com.sparta.week3_2.domain.comment.CommentResponseDto;
import com.sparta.week3_2.security.UserDetailsImpl;
import com.sparta.week3_2.service.BoardService;
import com.sparta.week3_2.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {


    private final BoardService boardService;
    private final CommentService commentService;

    @ResponseBody
    @GetMapping("/board")
        public List<BoardResponseDto> getList(Model model) {
        return boardService.getBoardList();
    }

    @GetMapping("/board/detail/{id}")
    public String detail(@PathVariable Long id, Model model ,@AuthenticationPrincipal UserDetailsImpl userDetails) {
       Board board = boardService.detailPost(id);
       List<CommentResponseDto> commentList = commentService.getCommentList(id);

        model.addAttribute("boards",board);
        return "detail";
    }

    @GetMapping("/auth/board/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        log.info("업데이트폼={}",id);
        Board board = boardService.detailPost(id);
        model.addAttribute("boards",board);
        return "updateForm";
    }

    @PostMapping("/auth/board")
    public String addPost(@RequestBody BoardRequestDto boardRequestDto,Model model) {
        log.info("Post요청={}","ADDPOST 실행");
        System.out.println(boardRequestDto.getContent()+"값 테스트");
        Board board = boardService.savePost(boardRequestDto);

        model.addAttribute("boards", board);
        return "redirect:/";
    }

    @ResponseBody
    @PutMapping("/auth/board/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.update(id, boardRequestDto);
    }

    @ResponseBody
    @DeleteMapping("/auth/board/{id}")
    public String delPost(@PathVariable Long id) {
        log.info("삭제호출={}",id);
        return boardService.delete(id);
    }

    @ResponseBody
    @PostMapping("/board/{id}")
    public Boolean validPassword(@PathVariable Long id, @RequestBody BoardValidDto boardValidDto) {
        return boardService.validPwd(id, boardValidDto);
    }

    @GetMapping("/add")
    public String addPost() {
        return "addPost";
    }

}

