package com.live.vladislav.Services;

import com.live.vladislav.Models.Todo;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
public class TodoLayout extends VerticalLayout {
    @Autowired
    TodoRepository repository;

    @PostConstruct
    void init(){
        setToDos(repository.findAll());
    }

    private void setToDos(List<Todo> toDos) {
        removeAll();

        toDos.forEach(toDo -> add(new ToDoItemLayout(toDo)));
    }
}
