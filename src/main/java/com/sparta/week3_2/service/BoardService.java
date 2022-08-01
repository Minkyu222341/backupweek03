package com.sparta.week3_2.service;

import com.sparta.week3_2.domain.User.User;
import com.sparta.week3_2.domain.article.Board;
import com.sparta.week3_2.domain.article.BoardRequestDto;
import com.sparta.week3_2.domain.article.BoardResponseDto;
import com.sparta.week3_2.repository.BoardRepository;
import com.sparta.week3_2.repository.UserRepository;
import com.sparta.week3_2.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public String update(Long id, BoardRequestDto boardRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        return validUpdate(boardRequestDto, userDetails, board);
    }

    private String validUpdate(BoardRequestDto boardRequestDto, UserDetailsImpl userDetails, Board board) {
        if(userDetails.getUser().getId().equals(board.getUser().getId())){
            board.update(boardRequestDto, userDetails.getUser());
            return board.getId()+"번 수정완료";
        }
        return "작성자가 아닙니다";
    }

    public String delete(Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다"));
        return validDelete(userDetails, board);
    }

    private String validDelete(UserDetailsImpl userDetails, Board board) {
        if (board.getId().equals(userDetails.getUser().getId())) {
            boardRepository.deleteById(board.getId());
            return board.getId()+"번 삭제완료";
        }
        return "작성자가 아닙니다";
    }

    public List<BoardResponseDto> getBoardList() {
        List<Board> all = boardRepository.findAllByOrderByCreatedAtDesc();
        List<BoardResponseDto> responseDto = new ArrayList<>();
        for (Board board : all) {
            String username = board.getUser().getUsername();
            responseDto.add(new BoardResponseDto(board,username));
            log.info("게시글번호당 유저이름");
        }
        return responseDto;
    }

    public Board detailPost(Long id) {
        return boardRepository.findById(id).orElseThrow((() -> new IllegalArgumentException("아이디가 존재하지 않습니다")));

    }

    public Board savePost(BoardRequestDto boardRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("유저 이름={} ", userDetails.getUsername());
        log.info("게시글 정보={}",boardRequestDto.getTitle());
        User user = userDetails.getUser();
        Board board = new Board(boardRequestDto,user);
        log.info("작성자정보={}", board.getUser().getUsername());
        return boardRepository.save(board);
    }

}