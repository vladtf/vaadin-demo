package com.live.vladislav.Services;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@SpringComponent
public class TodoLayout extends VerticalLayout {
    private TodoLayout()
    {
        add(new Button("Auto wired"));
    }
}
