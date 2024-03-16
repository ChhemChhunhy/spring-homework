package co.istad.todolist.repository;

import co.istad.todolist.model.Todo;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class TodoListDataSource {
    private final List<Todo> todoList = new ArrayList<>();
    public TodoListDataSource(){
        todoList.add(new Todo(1,"Do Homework","Do homework for today",false, LocalDate.now()));
        todoList.add(new Todo(2,"Buy food","Buy food for today",true, LocalDate.now()));
        todoList.add(new Todo(3,"Do homework Spring","Homewok need to finish for today ",true, LocalDate.now()));
        todoList.add(new Todo(4,"Do homework Next.js","Homewok need to finish for tomorrow. Deadline tomorrow ",false, LocalDate.now()));
        todoList.add(new Todo(5,"Do homework C#","Homewok need to finish for tomorrow. Deadline tomorrow ",false, LocalDate.now()));
        todoList.add(new Todo(6,"Do homework Java","Homewok need to finish for tomorrow. Deadline tomorrow ",false, LocalDate.now()));
        todoList.add(new Todo(7,"Do homework Angular","Homewok need to finish for tomorrow. Deadline tomorrow ",false, LocalDate.now()));
        todoList.add(new Todo(8,"Do homework React","Homewok need to finish for tomorrow. Deadline tomorrow ",false, LocalDate.now()));
        todoList.add(new Todo(9,"Do homework Vue","Homewok need to finish for tomorrow. Deadline tomorrow ",false, LocalDate.now()));

    }
}
