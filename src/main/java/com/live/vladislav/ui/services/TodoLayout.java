package com.live.vladislav.ui.services;

import com.live.vladislav.ui.models.Todo;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@UIScope
@SpringComponent
public class TodoLayout extends VerticalLayout implements TodoChangeListener {
    @Autowired
    TodoRepository repository;
    private List<Todo> toDos;

    @PostConstruct
    void init() {
        setWidth("80%");
        update();
    }

    private void update() {
        setToDos(repository.findAll());
    }

    private void setToDos(List<Todo> toDos) {
        removeAll();
        this.toDos = toDos;
        toDos.forEach(toDo -> add(new ToDoItemLayout(toDo, this)));
    }

    public void add(Todo todo) {
        repository.save(todo);
        update();
    }

    void addTodo(Todo todo) {
        repository.save(todo);
        update();
    }

    @Override
    public void todoChanged(Todo todo) {
        addTodo(todo);
    }

    public void deleteCompleted() {
        repository.deleteByDone(true);
        update();
    }
}
