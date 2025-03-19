package com.example.project4team.service;

import com.example.project4team.dto.BoardDTO;
import com.example.project4team.dto.FreeDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FreeService {

    //등록
    public FreeDTO insertFree(FreeDTO freeDTO);


    //목록
    public List<FreeDTO> listFree(FreeDTO freeDTO,Pageable pageable);

    //읽기
    public FreeDTO readFree(Long fno);


    //수정
    public FreeDTO modifyFree(FreeDTO freeDTO);


    //삭제
    public void delFree(Long fno);


    //페이지 카운터 처리
    public int pageCount(Pageable pageable);


}
