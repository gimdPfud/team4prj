package com.example.project4team.controller;


import com.example.project4team.dto.FreeDTO;
import com.example.project4team.service.FreeService;
import lombok.RequiredArgsConstructor;


import lombok.Value;
import lombok.extern.log4j.Log4j2;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/free") //free/insert
public class FreeController {


    private final FreeService freeService;


    @GetMapping("/insert")
    public String insertGet(FreeDTO freeDTO){

        return "freeinsert";
    }

    @PostMapping("/insert")
    public String insertPost(FreeDTO freeDTO){

        log.info("post 등록 됐니" + freeDTO);

        //저장 하기
        freeService.insertFree(freeDTO);

        return "/freeinsert";
    }

    @GetMapping("/list")
    public String listGet(Model model,FreeDTO freeDTO,
        @RequestParam(value = "page",defaultValue = "1")int page)
    {

        log.info("get방식 페이지 진입");
        log.info("get방식 페이지 진입" + freeDTO);

        Pageable pageable = PageRequest.of(page-1,8);

        List<FreeDTO> freeDTOList =
                freeService.listFree(freeDTO,pageable);

        int count =
                freeService.pageCount(pageable);

        model.addAttribute("freeDTOList",freeDTOList);
        model.addAttribute("page",page);
        model.addAttribute("count", count);

        return "/freelist";



    }

    @GetMapping("/read")
    public String readGet(Model model,Long fno){

        if(fno == null) {
            return "redirect:/free/list";

        }

        log.info("리드 페이지에 들어 왔니 :" + fno);


        FreeDTO freeDTO =
        freeService.readFree(fno);

        model.addAttribute("freeDTO", freeDTO);


        return "/freeread";
    }

}
