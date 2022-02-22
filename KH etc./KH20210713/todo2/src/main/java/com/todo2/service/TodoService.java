package com.todo2.service;

import com.todo2.dao.TodoEntity;
import com.todo2.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoEntity> findAllTodo(){
        return todoRepository.findAll();
    }
}
