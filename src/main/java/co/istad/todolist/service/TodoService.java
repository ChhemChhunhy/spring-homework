package co.istad.todolist.service;

import co.istad.todolist.model.Todo;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TodoService {
    List<Todo> displayTodos();
    Todo findById(int id);
    void addTask(Todo todo);
    void updateTask(Todo todo);
     void deleteTask(Integer id);
     List<Todo> searchTasl(String task,Boolean isDone);

}
