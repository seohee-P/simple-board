package com.seohee.board.service;

import com.seohee.board.dto.BoardRequestDto;
import com.seohee.board.dto.DeleteBoardRequestDto;
import com.seohee.board.dto.UpdateBoardRequestDto;
import com.seohee.board.entity.Board;
import com.seohee.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public Board save(BoardRequestDto requestDto) {
        return boardRepository.save(requestDto.toEntity());
    }

    public List<Board> findAll() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id+"번 게시판 글은 존재하지 않습니다."));
    }

    public void delete(Long id, DeleteBoardRequestDto requestDto) {
         Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "번 게시판 글은 존재하지 않습니다."));

        if (!board.getPassword().equals(requestDto.getPassword())) {
            throw new WrongPasswordException();
        }

        boardRepository.deleteById(id);

    }


    @Transactional
    public Board update(Long id, UpdateBoardRequestDto requestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "번 게시판 글은 존재하지 않습니다."));

        if (!board.getPassword().equals(requestDto.getPassword())) {
            throw new WrongPasswordException();
        }

        board.update(requestDto);
        return board;
    }

    static class WrongPasswordException extends RuntimeException {
        public WrongPasswordException() {
            super("비밀번호가 틀렸습니다.");
        }
    }

}
