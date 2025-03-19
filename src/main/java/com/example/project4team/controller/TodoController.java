package com.example.project4team.controller;

import com.example.project4team.dto.TodoDTO;
import com.example.project4team.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/insert")
    public String insertget(){
        log.info("insert");
        return "/todoinsert";
    }
    @PostMapping("/insert")//
    public String insertPost(TodoDTO todoDTO) {
        log.info("insert post"+todoDTO);
        todoService.insertTodo(todoDTO);
        return "/todoinsert";
    }





}
