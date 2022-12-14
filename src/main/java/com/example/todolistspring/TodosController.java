package com.example.todolistspring;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class TodosController {
    protected List<Todo> todos = new ArrayList<Todo>();

    @GetMapping("/todos")
    public List<Todo> getTodos() {
        return todos;
    }

    @GetMapping("/todos/{id}")
    public Todo getTodo(@PathVariable int id) {
        return todos.get(id);
    }

    @PostMapping("/todos")
    public ApiResponse createTodo(@RequestBody Todo todo) {
        todos.add(todo);
        return new ApiResponse("Todo added successfully");
    }

    @PutMapping("/todos/update/{id}")
    public ApiResponse updateTodo(@PathVariable int id, @RequestBody Todo todo) {
        String oldTodo = todo.getTitle();
        todos.set(id, todo);
        return new ApiResponse("Your Todo: " + oldTodo + " updated successfully");
    }

    @DeleteMapping("/todos/{id}")
    public ApiResponse destroyTodo(@PathVariable int id) {
        String oldTodo = todos.get(id).getTitle();
        todos.remove(id);
        return new ApiResponse("Your Todo: " + oldTodo + " has been removed successfully");
    }

    @GetMapping("/todos/search")
    public ApiResponse searchTodo(@RequestParam(value = "title", defaultValue = "") String todoTitle) {
        String msg = "";
        for (Todo todo : todos) {
            msg = todo.getTitle().contains(todoTitle) ? todo.getTitle() : "not found";
        }

        return new ApiResponse(msg);
    }
}
