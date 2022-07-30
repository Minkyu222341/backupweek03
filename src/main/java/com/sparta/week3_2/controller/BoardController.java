package com.sparta.week3_2.controller;

import com.sparta.week3_2.domain.article.Board;
import com.sparta.week3_2.domain.article.BoardResponseDto;
import com.sparta.week3_2.domain.article.BoardRequestDto;
import com.sparta.week3_2.domain.article.BoardValidDto;
import com.sparta.week3_2.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {


    private final BoardService boardService;

    @ResponseBody
    @GetMapping("/board")
        public List<BoardResponseDto> getList(Model model) {
        return boardService.getBoardList();
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Board> board = boardService.detailPost(id);
        model.addAttribute("boards",board.get());
        return "detail";
    }

    @GetMapping("/board/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        log.info("업데이트폼={}",id);
        Optional<Board> board = boardService.detailPost(id);
        model.addAttribute("boards",board.get());
        return "updateForm";
    }

    @PostMapping("/board")
    public String addPost(@RequestBody BoardRequestDto boardRequestDto,Model model) {
        log.info("Post요청={}","ADDPOST 실행");
        System.out.println(boardRequestDto.getContent()+"값 테스트");
        Board board = boardService.savePost(boardRequestDto);

        model.addAttribute("boards", board);
        return "redirect:/";
    }

    @ResponseBody
    @PutMapping("/board/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.update(id, boardRequestDto);
    }

    @ResponseBody
    @DeleteMapping("/board/{id}")
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

