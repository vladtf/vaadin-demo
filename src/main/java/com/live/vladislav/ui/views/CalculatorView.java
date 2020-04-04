package com.live.vladislav.ui.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@PageTitle("calculator")
@UIScope
@Route(value = "calculator", layout = MainLayout.class)
public class CalculatorView extends VerticalLayout {
    public CalculatorView() {
    }
}
