package co.istad.todolist.controller;

import co.istad.todolist.model.Todo;
import co.istad.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    @GetMapping("/todo")
    public String index(Model model){
        List<Todo> todoList = todoService.displayTodos();
        model.addAttribute("todos",todoList);
        return "index";
    }

    @GetMapping("/todo/{id}")
    public String detailPage(@PathVariable int id, Model model){
        Todo todoList = todoService.findById(id);
        model.addAttribute("todos",todoList);
        return "index";
     }

     @GetMapping("/todo/new")
     public String create(Model model){
        Todo todo = new Todo();
        model.addAttribute("todo",todo);
        return "create";
    }

    @PostMapping("todo/new")
    public String createForm(@ModelAttribute("todo") Todo todo){
        todoService.addTask(todo);
        return "redirect:/todo";
    }
    @GetMapping("/todo/edit/{id}")
    public String updateTask(@PathVariable("id") Integer id,Model model){
        Todo findTask = todoService.findById(id);
        model.addAttribute("todo",findTask);
        return "update";
    }

    @PostMapping("/todo/edit")
    public String updateTask(@ModelAttribute("todo") Todo todo){
        todoService.updateTask(todo);
        return "redirect:/todo";
    }

        @GetMapping("/todo/delete/{id}")
    public String deleteTask(@PathVariable("id") Integer id){
        todoService.deleteTask(id);
        return "redirect:/todo";
    }
    @GetMapping("todo/search")
    public String searchTask(@RequestParam(required = false) String task,
                             @RequestParam(required = false)Boolean isDone, Model model){
        List<Todo> searchResult = todoService.searchTasl(task,isDone);
        model.addAttribute("todos", searchResult);
        return "index";
    }
}
