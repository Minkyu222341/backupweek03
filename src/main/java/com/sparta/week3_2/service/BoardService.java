package com.sparta.week3_2.service;

import com.sparta.week3_2.domain.article.Board;
import com.sparta.week3_2.domain.article.BoardResponseDto;
import com.sparta.week3_2.domain.article.BoardRequestDto;
import com.sparta.week3_2.domain.article.BoardValidDto;
import com.sparta.week3_2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public String update(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        log.info("board password={}", board.getPassword());
        log.info("update password={}", boardRequestDto.getPassword());
        System.out.println(boardRequestDto.getAuthor()+"작성자는");
        if(board.getPassword().equals(boardRequestDto.getPassword())){
            board.update(boardRequestDto);
            return board.getId()+"번 수정완료";
        }else {
            return "비밀번호를 확인해주세요";
        }
    }

    public String delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다"));
        boardRepository.deleteById(board.getId());
        return board.getId()+"번 삭제완료";
    }

    public Boolean validPwd(Long id, BoardValidDto boardValidDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다"));
        if (board.getPassword().equals(boardValidDto.getPassword())) {
            return true;
        }else{
            return false;
        }
    }
    public List<BoardResponseDto> getBoardList() {
        List<Board> all = boardRepository.findAllByOrderByCreatedAtDesc();
        List<BoardResponseDto> responseDto = new ArrayList<>();
        for (Board board : all) {
            responseDto.add(new BoardResponseDto(board));
        }
        return responseDto;
    }

    public Optional<Board> detailPost(Long id) {
        return boardRepository.findById(id);
    }

    public Board savePost(BoardRequestDto boardRequestDto) {
        Board board = new Board(boardRequestDto);
        return boardRepository.save(board);
    }

}