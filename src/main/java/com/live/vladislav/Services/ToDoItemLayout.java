package com.live.vladislav.Services;

import com.live.vladislav.Models.Todo;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;


public class ToDoItemLayout extends HorizontalLayout {

    private final Checkbox done;
    private final TextField text;

    public ToDoItemLayout(Todo toDo) {
        done = new Checkbox();
        text = new TextField();

        add(done, text);
    }
}
