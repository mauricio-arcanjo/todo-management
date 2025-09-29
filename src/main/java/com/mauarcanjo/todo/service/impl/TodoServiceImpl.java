package com.mauarcanjo.todo.service.impl;

import com.mauarcanjo.todo.dto.TodoDto;
import com.mauarcanjo.todo.entity.Todo;
import com.mauarcanjo.todo.exception.ResourceNotFoundException;
import com.mauarcanjo.todo.mapper.TodoMapper;
import com.mauarcanjo.todo.repository.TodoRepository;
import com.mauarcanjo.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoDto addTodo(TodoDto todoDto) {

        Todo savedTodo = todoRepository.save(
                TodoMapper.mapToTodo(todoDto)
        );

        return TodoMapper.mapToTodoDto(savedTodo);
    }

    public TodoDto getTodoById(Long id) {

        Todo todo = getTodo(id);

        return TodoMapper.mapToTodoDto(todo);
    }

    public List<TodoDto> getAllTodos() {

        List<Todo> todos = todoRepository.findAll();

        return todos.stream()
                .map(TodoMapper::mapToTodoDto)
                .toList();
    }

    @Transactional
    public TodoDto updateTodo(TodoDto todoDto) {

        Todo todo = getTodo(todoDto.getId());

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        return TodoMapper.mapToTodoDto(todo);
    }

    public void deleteTodo(Long id) {

        Todo todo = getTodo(id);
        todoRepository.deleteById(id);

    }

    @Transactional
    public TodoDto completeTodo(Long id) {

        Todo todo = changeCompleteStatus(id, Boolean.TRUE);
        return TodoMapper.mapToTodoDto(todo);
    }

    @Transactional
    public TodoDto inCompleteTodo(Long id) {

        Todo todo = changeCompleteStatus(id, Boolean.FALSE);
        return TodoMapper.mapToTodoDto(todo);
    }

    private Todo changeCompleteStatus (Long id, Boolean status){
        Todo todo = getTodo(id);
        todo.setCompleted(status);
        return todo;
    }

    private Todo getTodo (Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with ID " + id));
    }

}
