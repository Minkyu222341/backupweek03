package com.sparta.week3_2.service;

import com.sparta.week3_2.domain.article.Board;
import com.sparta.week3_2.domain.comment.Comment;
import com.sparta.week3_2.domain.comment.CommentRequestDto;
import com.sparta.week3_2.domain.comment.CommentResponseDto;
import com.sparta.week3_2.repository.BoardRepository;
import com.sparta.week3_2.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;


    @Transactional
    public Long commentSave(Long id, CommentRequestDto dto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));
        log.info("Service 값 테스트 내용={}", dto.getText());
        
        Comment comment = new Comment(dto,board);
        commentRepository.save(comment);
        return dto.getId();
    }

    public List<CommentResponseDto> getCommentList(Long id) {
        List<Comment> comments = commentRepository.findByBoard_Id(id);
        List<CommentResponseDto> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            dtos.add(new CommentResponseDto(comment));
        }
        return dtos;
    }



}
