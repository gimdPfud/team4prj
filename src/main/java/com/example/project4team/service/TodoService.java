package com.example.project4team.service;

import com.example.project4team.dto.BoardDTO;
import com.example.project4team.dto.TodoDTO;
import com.example.project4team.entity.Todo;

import java.util.List;

public interface TodoService {
    //등록
    public TodoDTO insertTodo(TodoDTO todoDTO);

    //목록
    public List<TodoDTO> listTodo(String writer);

    //읽기
    public TodoDTO readTodo(long tno);

    //수정
    public TodoDTO modifyTodo(TodoDTO todoDTO);

    //삭제
    public void delTodo(Long tno);

}
