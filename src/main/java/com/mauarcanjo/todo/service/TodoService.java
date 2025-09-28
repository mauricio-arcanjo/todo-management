package com.mauarcanjo.todo.service;

import com.mauarcanjo.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodoById(Long id);
    List<TodoDto> getAllTodos();
    TodoDto updateTodo (TodoDto todoDto);
    void deleteTodo (Long id);
    TodoDto completeTodo(Long id);
    TodoDto inCompleteTodo(Long id);


}
