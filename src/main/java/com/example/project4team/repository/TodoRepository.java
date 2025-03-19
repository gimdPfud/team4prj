package com.example.project4team.repository;

import com.example.project4team.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    //작성자가 쓴 글만 가져오기
    public List<Todo> findByWiter(String todo);

}
