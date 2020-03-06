package com.live.vladislav.Views;

import com.live.vladislav.Application;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "sandbox")
public class SandBoxView extends VerticalLayout {
    public SandBoxView() {
        add(new Button("Go back", buttonClickEvent -> UI.getCurrent().navigate("root")));
    }
}