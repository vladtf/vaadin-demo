package com.live.vladislav.ui.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@PageTitle("MineSweeper")
@UIScope
@Route(value = "minesweeper", layout = MainLayout.class)
public class MineSweeperView extends VerticalLayout {

    private VerticalLayout mainLayout;
    private final String FLAG_ICON = "\uD83C\uDFF3";
    private final String BOMB_ICON = "\uD83D\uDCA3";
    private final String EMPTY_ICON = "";

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
        for (int i = 0; i < 10; i++) {
            HorizontalLayout row = new HorizontalLayout();
            for (int j = 0; j < 10; j++) {
                Button button = new Button();

                button.addClickListener(clickEvent -> {
                    if (button.getText().equals(FLAG_ICON)) {
                        button.setText(EMPTY_ICON);
                    } else {
                        button.setText(FLAG_ICON);
                    }
                    System.out.println(clickEvent.getButton());
                });
                button.setText(BOMB_ICON);
                row.add(button);
            }
            mainLayout.add(row);
            mainLayout.setHorizontalComponentAlignment(Alignment.CENTER, row);
        }

    }
}
