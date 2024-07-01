package com.seohee.board.controller;

import com.seohee.board.dto.BoardRequestDto;
import com.seohee.board.dto.BoardResponseDto;
import com.seohee.board.dto.DeleteBoardRequestDto;
import com.seohee.board.dto.UpdateBoardRequestDto;
import com.seohee.board.entity.Board;
import com.seohee.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto){
        boardService.save(requestDto);
        BoardResponseDto responseDto = new BoardResponseDto(new Board(requestDto));
        return responseDto;
    }

    @GetMapping("/boards")
    public List<BoardResponseDto> getAllBoards(){
        List<BoardResponseDto> boards = boardService.findAll()
                .stream().map(BoardResponseDto::new).collect(Collectors.toList());
        return boards;
    }

    @GetMapping("/board/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id){
        return new BoardResponseDto(boardService.findById(id));
    }

    @PutMapping("/board/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody UpdateBoardRequestDto requestDto){
        boardService.update(id, requestDto);
        return new BoardResponseDto(boardService.findById(id));
    }


    @DeleteMapping("/board/{id}")
    public String deleteBoard(@PathVariable Long id, @RequestBody DeleteBoardRequestDto requestDto){
        boardService.delete(id, requestDto);
        return "id "+id+" was successfully deleted.";
    }
}
