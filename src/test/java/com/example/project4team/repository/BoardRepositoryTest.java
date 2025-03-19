package com.example.project4team.repository;

import com.example.project4team.entity.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void insertTest(){

        for (int i = 0; i < 20; i++) {
            Board board = new Board();
            board.setTitle("제목 테스트 . . " + i);
            board.setContent("내용 테스트 . . " + i);
            board.setWriter("작성자 테스트 . . " + i);
            boardRepository.save(board);

        }

    }

}