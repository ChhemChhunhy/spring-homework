package co.istad.todolist.service;

import co.istad.todolist.model.Todo;
import co.istad.todolist.repository.TodoListDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoListDataSource todoListDataSource;
    @Override
    public List<Todo> displayTodos() {
        return todoListDataSource.getTodoList();
    }

    @Override
    public Todo findById(int id) {
        return todoListDataSource.getTodoList().stream().filter(todo -> todo.getId()==id).findFirst().orElse(null);
    }

    @Override
    public void addTask(Todo todo) {
        List<Todo> todoList = todoListDataSource.getTodoList();
        Todo lastTodo = null;
        if (!todoList.isEmpty()) {
            lastTodo = todoList.get(todoList.size() - 1);
        }
        int nextId = 1; // Default ID if no tasks exist
        if (lastTodo != null) {
            Integer lastId = lastTodo.getId();
            if (lastId != null) {
                nextId = lastId + 1;
            }
        }
        todo.setId(nextId);
        todoList.add(todo);
    }

    @Override
    public void updateTask(Todo todo) {
        todoListDataSource.getTodoList().stream()
                .filter(p -> p.getId().equals(todo.getId()))
                .findFirst()
                .ifPresent(p -> {
                    p.setTask(todo.getTask());
                    p.setDescription(todo.getDescription());
                    p.setIsDone(todo.getIsDone());
                    p.setCreatedAt(todo.getCreatedAt());
                });
    }


    @Override
    public void deleteTask(Integer id) {
        List<Todo> todoList = todoListDataSource.getTodoList();
        for (Todo todo : todoList) {
            if(todo.getId().equals(id)) {
                todoList.remove(todo);
                break;
            }
        }
    }

    @Override
    public List<Todo> searchTasl(String task, Boolean isDone) {
        List<Todo> todoList = todoListDataSource.getTodoList();

        // Filter the todoList based on task and isDone
        List<Todo> filteredList = todoList.stream()
                .filter(todo -> {
                    boolean taskMatch = (task == null || todo.getTask().toLowerCase().contains(task.trim().toLowerCase()));
                    boolean isDoneMatch = (isDone == null || (todo.getIsDone() != null && todo.getIsDone().equals(isDone)));

                    return taskMatch && isDoneMatch;
                })
                .collect(Collectors.toList());

        // Check if any tasks match the search criteria
        boolean anyTasksMatch = filteredList.stream()
                .anyMatch(todo -> todo.getTask().toLowerCase().contains(task.trim().toLowerCase()));

        // If no tasks match the search criteria, return an empty list
        if (!anyTasksMatch) {
            return List.of();
        }

        return filteredList;
    }
}
