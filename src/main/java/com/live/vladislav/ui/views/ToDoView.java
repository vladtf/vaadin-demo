package com.live.vladislav.ui.views;

import com.live.vladislav.ui.models.Todo;
import com.live.vladislav.ui.services.TodoLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@PageTitle("ToDo")
@Route(value = "todo")
@Theme(value = Lumo.class)
@UIScope
public class ToDoView extends VerticalLayout {

    @Autowired
    public TodoLayout todoLayout;
    private VerticalLayout root;

    @PostConstruct
    private void init() {
        add(new Button("Back", buttonClickEvent ->
                UI.getCurrent().navigate(MainView.class)));
        setupLayout();
        addHeader();
        addForm();
        addToDoList();
        addDeleteButton();
    }

    private void setupLayout() {
        root = new VerticalLayout();
        root.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        add(root);
    }

    private void addHeader() {
        Label header = new Label("TODOs");
        root.add(header);
    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();

        TextField task = new TextField();
        task.focus();

        Button add = new Button("");
        add.setIcon(VaadinIcon.PLUS.create());
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        formLayout.addAndExpand(task);
        formLayout.add(add);

        add.addClickListener(buttonClickEvent -> {
            if (!task.getValue().isEmpty()) {
                todoLayout.add(new Todo(task.getValue()));
                task.setValue("");
                task.focus();
            } else {
                Notification.show("Text field is empty");
            }
        });
        add.addClickShortcut(Key.ENTER);
        formLayout.setWidth("80%");
        root.add(formLayout);
    }

    private void addToDoList() {
        todoLayout.setWidth("80%");
        root.add(todoLayout);
    }


    private void addDeleteButton() {
        root.add(new Button("Delete completed", click -> todoLayout.deleteCompleted()));
    }

}
