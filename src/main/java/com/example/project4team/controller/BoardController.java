package com.example.project4team.controller;

import com.example.project4team.dto.BoardDTO;
import com.example.project4team.entity.Board;
import com.example.project4team.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Log4j2
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String listBoard(@PageableDefault(size = 10, sort = "boardNum", direction = Sort.Direction.DESC)
                                Pageable pageable, Model model){

        Page<BoardDTO> boardDTOS = boardService.listBoard(pageable);
        model.addAttribute("boardDTOS", boardDTOS);

        return "boardlist";    //파일로 들어가는 것
    }

    @GetMapping("/read/{boardNum}")
    public String readBoard(@PathVariable("boardNum") Long boardNum, Model model){

        BoardDTO boardDTO = boardService.readBoard(boardNum);
        model.addAttribute("boardDTO", boardDTO);

        return "boardread";
    }

    @GetMapping("/insert")
    public String insertBoard(){

        return "boardinsert";
    }

    @PostMapping("/insert")
    public String insertBoard(BoardDTO boardDTO){

        boardService.insertBoard(boardDTO);

        return "redirect:/board/list";
    }

    @GetMapping("/modify/{boardNum}")
    public String modifyBoard(@PathVariable("boardNum") Long boardNum, Model model){
//        BoardDTO boardDTO = boardService.readBoard(boardNum);
//        model.addAttribute("boardDTO", boardDTO);
        model.addAttribute("boardDTO",  boardService.readBoard(boardNum));
        return "boardmodify";
    }

    @PostMapping("/modify")
    public String modifyBoard(BoardDTO boardDTO){
        log.info("수정 페이지 진입 : " + boardDTO);

        boardService.modifyBoard(boardDTO);

        return "redirect:/board/read/" + boardDTO.getBoardNum();      //redirect : 주소(경로)로 가는 것
    }

    @GetMapping("/del/{boardNum}")
    public String delBoard(@PathVariable("boardNum") Long boardNum) {

        log.info("삭제할 값 : " + boardNum);

        if(boardNum != null) {   //bno가 null이 아니라면, 값이 있다면
            boardService.delBoard(boardNum);  //삭제
        }

        return "redirect:/board/list";
    }


}
