package com.mauarcanjo.todo.controller;

import com.mauarcanjo.todo.dto.TodoDto;
import com.mauarcanjo.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/todos")
public class TodoController {

    private final TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')") // so usa para method security
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){

        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')") // so usa para method security
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") Long id){

        TodoDto todoDto = todoService.getTodoById(id);
        return ResponseEntity.ok(todoDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')") // so usa para method security
    @GetMapping({"/getall", "/all"})
    public ResponseEntity<List<TodoDto>> getAllTodo(){

        List<TodoDto> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @PreAuthorize("hasRole('ADMIN')") // so usa para method security
    @PutMapping
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto){

        TodoDto savedTodo = todoService.updateTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')") // so usa para method security
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){

        todoService.deleteTodo(id);

        return ResponseEntity.ok("Todo successfuly deleted!");

    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')") // so usa para method security
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeStatus (@PathVariable("id") Long id){

        TodoDto todoDto = todoService.completeTodo(id);
        return ResponseEntity.ok(todoDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')") // so usa para method security
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> inCompleteStatus (@PathVariable("id") Long id){

        TodoDto todoDto = todoService.inCompleteTodo(id);
        return ResponseEntity.ok(todoDto);
    }

}
