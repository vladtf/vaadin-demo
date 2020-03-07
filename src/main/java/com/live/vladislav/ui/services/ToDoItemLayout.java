package com.live.vladislav.ui.services;

import com.live.vladislav.ui.models.Todo;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ToDoItemLayout extends HorizontalLayout {

    private final Checkbox done;
    private final TextField text;

    public ToDoItemLayout(Todo toDo, TodoChangeListener changeListener) {
        done = new Checkbox();
        text = new TextField();

        Binder<Todo> binder = new Binder<>(Todo.class);
        binder.bindInstanceFields(this);
        binder.setBean(toDo);

        add(done);
        addAndExpand(text);

        binder.addValueChangeListener(valueChangeEvent -> changeListener.todoChanged(toDo));


    }
}
