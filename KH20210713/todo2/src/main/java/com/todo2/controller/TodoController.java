package com.todo2.controller;

import com.todo2.dao.TodoEntity;
import com.todo2.service.TodoService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class TodoController {

    private final TodoService todoService;


    @GetMapping("/top")
    public String top(){
        List<TodoEntity> todoEntityList = todoService.findAllTodo();
        return "top";
    }

}
