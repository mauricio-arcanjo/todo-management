package com.mauarcanjo.todo.mapper;

import com.mauarcanjo.todo.dto.TodoDto;
import com.mauarcanjo.todo.entity.Todo;

public class TodoMapper {

    public static Todo mapToTodo (TodoDto todoDto){
        return new Todo(
                null,
                todoDto.getTitle(),
                todoDto.getDescription(),
                todoDto.isCompleted()
        );
    }

    public static TodoDto mapToTodoDto (Todo todo){
        return new TodoDto(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted()
        );
    }
}
