package com.live.vladislav.ui.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@PageTitle("MineSweeper")
@UIScope
@Route(value = "minesweeper", layout = MainLayout.class)
public class MineSweeperView extends VerticalLayout {

    private VerticalLayout mainLayout;

    public MineSweeperView() {
        setupGrid();
        createPlayGround();
    }

    private void setupGrid() {
        mainLayout = new VerticalLayout();

        mainLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(mainLayout);
    }

    private void createPlayGround() {
        Button button = new Button();
        button.setWidth("80%");

        button.addClickListener(clickEvent -> {
            if (button.getIcon() == null) {
                button.setIcon(VaadinIcon.FLAG.create());
            } else {
                button.setIcon(null);
            }
        });

        mainLayout.add(button);
    }
}
