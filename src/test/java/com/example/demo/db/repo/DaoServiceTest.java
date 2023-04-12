package com.example.demo.db.repo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.db.entity.Todo;
import com.example.demo.service.DaoServiceImpl;

//@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class DaoServiceTest
{
    private AutoCloseable closeable;

    @InjectMocks
    private DaoServiceImpl daoService;

    @Mock
    private TodoDao todoDao;

    @BeforeAll
    public void openMocks()
    {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterAll
    public void releaseMocks() throws Exception
    {
        closeable.close();
    }


    @Test
    public void testFindAll()
    {
        List<Todo> expectedTodos = new ArrayList<>(1);
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("fake task");
        todo.setStatus(0);
        expectedTodos.add(todo);
        Mockito.when(todoDao.findAll()).thenReturn(expectedTodos);

        List<Todo> actualTodos = daoService.getAllTodos();

        Assertions.assertEquals(expectedTodos, actualTodos);
    }
}
