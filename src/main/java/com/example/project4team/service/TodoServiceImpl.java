package com.example.project4team.service;

import com.example.project4team.dto.TodoDTO;
import com.example.project4team.entity.Todo;
import com.example.project4team.repository.TodoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional

public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final MembersService membersService;

    //등록
    @Override
    public TodoDTO insertTodo(TodoDTO todoDTO) {

        Todo todo = modelMapper.map(todoDTO ,Todo.class);
        todo=
                todoRepository.save(todo);
        log.info("등록");

        todoDTO = modelMapper.map(todo,TodoDTO.class);
        return todoDTO;
    }
    //목록
    @Override
    public List<TodoDTO> listTodo(String witer) {

        List<Todo> todoList=
                todoRepository.findByWiter(witer);


        List<TodoDTO> todoDTOList = new ArrayList<>();

        for (Todo todo:todoList){

            TodoDTO todoDTO = modelMapper.map(todo,TodoDTO.class);

            todoDTOList.add(todoDTO);

        }

        return todoDTOList;
    }

    //읽기
    @Override
    public TodoDTO readTodo(long tno) {
        Optional<Todo> optionalTodo=
                todoRepository.findById(tno);

        //예외
        Todo todo=optionalTodo.orElseThrow(EntityNotFoundException::new);

        log.info("todo:" +todo);
        TodoDTO todoDTO =modelMapper.map(todo,TodoDTO.class);

        return todoDTO;
    }
    //수정
    @Override
    public TodoDTO modifyTodo(TodoDTO todoDTO) {

        //entity 값 찾아오기
        Optional<Todo> optionalTodo=
                todoRepository.findById(todoDTO.getTodonum());

        //예외
        Todo todo =
                optionalTodo.orElseThrow(EntityExistsException::new);

        todo.setTitle(todoDTO.getTitle());
        todo.setContent(todoDTO.getTitle());

        log.info("Todo"+todo);
        return modelMapper.map(todo,TodoDTO.class);

    }
    //삭제
    @Override
    public void delTodo(Long tno) {

        todoRepository.deleteById(tno);

    }
}