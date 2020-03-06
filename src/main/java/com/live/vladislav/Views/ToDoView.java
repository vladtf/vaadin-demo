package com.live.vladislav.Views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route(value = "todo")
public class ToDoView extends VerticalLayout {

    private VerticalLayout root;

    public ToDoView(){
        add(new Button("Go Back", buttonClickEvent -> UI.getCurrent().navigate("root")));

        setupLayout();
        addHeader();
        addForm();
        addToDoList();
        addDeleteButton();

    }

    private void setupLayout() {
        root = new VerticalLayout();
        add(root);
    }

    private void addHeader() {
        root.add(new Label("TODOs"));
    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();

        TextField task = new TextField();
        Button add = new Button("Add");

        formLayout.add(task,add);

        root.add(formLayout);
    }

    private void addToDoList() {

    }

    private void addDeleteButton() {

    }

}
