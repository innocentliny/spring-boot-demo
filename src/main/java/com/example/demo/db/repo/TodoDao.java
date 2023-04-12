package com.example.demo.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.db.entity.Todo;
import com.example.demo.dto.TodoDto;

@Repository
public interface TodoDao extends JpaRepository<Todo, Long>
{
    @Query("select new com.example.demo.dto.TodoDto(task, status) from Todo")
    List<TodoDto> findAllAsDto();


}
