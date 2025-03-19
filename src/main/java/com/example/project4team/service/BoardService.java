package com.example.project4team.service;

import com.example.project4team.dto.BoardDTO;
import com.example.project4team.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    //목록(페이징 처리된)
    public Page<BoardDTO> listBoard(Pageable pageable);

    //상세보기
    public BoardDTO readBoard(Long bno);

    //등록
    public void insertBoard(BoardDTO boardDTO);

    //수정
    public void modifyBoard(BoardDTO boardDTO);

    //삭제
    public void delBoard(Long bno);

}
