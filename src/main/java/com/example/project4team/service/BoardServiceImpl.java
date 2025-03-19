package com.example.project4team.service;

import com.example.project4team.dto.BoardDTO;
import com.example.project4team.entity.Board;
import com.example.project4team.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Page<BoardDTO> listBoard(Pageable pageable) {

        Page<Board> boards = boardRepository.findAll(pageable);

        return boards.map(board -> modelMapper.map(board, BoardDTO.class));
    }

    @Override
    public BoardDTO readBoard(Long bno) {

        Board board = boardRepository.findById(bno).orElseThrow();

        return modelMapper.map(board, BoardDTO.class);
    }

    @Override
    public void insertBoard(BoardDTO boardDTO) {

        Board board = modelMapper.map(boardDTO, Board.class);
        boardRepository.save(board);
    }

    @Override
    public void modifyBoard(BoardDTO boardDTO) {

        Board board = modelMapper.map(boardDTO, Board.class);
        boardRepository.save(board);
    }

    @Override
    public void delBoard(Long bno) {

        boardRepository.deleteById(bno);
    }


}






