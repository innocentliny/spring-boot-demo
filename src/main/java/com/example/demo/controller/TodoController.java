package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DaoService;
import com.example.demo.dto.TodoDto;
import com.example.demo.db.entity.Todo;

@RestController
@RequestMapping(path = "todos")
public class TodoController
{
    private DaoService daoService;

    public TodoController(@Autowired DaoService daoService)
    {
        this.daoService = daoService;
    }

    @GetMapping
    public List<Todo> getAll()
    {
        return daoService.getAllTodos();
    }

    @GetMapping("dto")
    public List<TodoDto> getAllAsDto()
    {
        return daoService.getAllAsDto();
    }
}
