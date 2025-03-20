package com.example.project4team.controller;

import com.example.project4team.dto.MembersDTO;
import com.example.project4team.dto.TodoDTO;
import com.example.project4team.service.MembersService;
import com.example.project4team.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;
    private final MembersService membersService;

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
    @GetMapping("/list")
    public String list(Model model, Principal principal) {
        String email = principal.getName();
        MembersDTO membersDTO = membersService.readMembers(email);
        String name = membersDTO.getName();
        List<TodoDTO> todoDTOList =todoService.listTodo(name);
        log.info("list"+todoDTOList);
        model.addAttribute("todoDTOList" ,todoDTOList);
        return "todoinsert";
    }
    @GetMapping("/read")
    public String read(Long tno,Model model) {
        TodoDTO todoDTO =todoService.readTodo(tno);
        log.info("read"+tno);
        model.addAttribute("todoDTO" ,todoDTO);
        return "todoinsert";
    }
    @GetMapping("/modify")
    public String modify(Long tno,Model model) {
        log.info("modify"+tno);
        if(tno==null){
            return "redirect:/todo/insert";
        }
        TodoDTO todoDTO = todoService.readTodo(tno);
        model.addAttribute("todoDTO" ,todoDTO);
        return "todoinsert";
    }
}
