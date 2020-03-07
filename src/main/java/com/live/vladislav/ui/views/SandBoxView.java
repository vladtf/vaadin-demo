package com.live.vladislav.ui.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@Route(value = "sandbox")
@UIScope
public class SandBoxView extends VerticalLayout {
    public SandBoxView() {
        add(new Button("Go back", buttonClickEvent -> UI.getCurrent().navigate("root")));
    }
}