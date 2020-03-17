package com.live.vladislav.ui.views;

import com.live.vladislav.ui.views.minesweeper.Tile;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

import java.util.Random;

@PageTitle("MineSweeper")
@UIScope
@Route(value = "minesweeper", layout = MainLayout.class)
@CssImport("./styles/minesweeper-styles.css")
public class MineSweeperView extends VerticalLayout {

    private VerticalLayout mainLayout;
    private final String FLAG_ICON = "\uD83C\uDFF3";
    private final String BOMB_ICON = "\uD83D\uDCA3";
    private final String EMPTY_ICON = "";
    private Tile[][] allTiles;
    private int groundWidth;
    private int groundHeight;

    public MineSweeperView() {
        setupGrid();
        addRestartButton();
        createPlayGround();
    }

    private void addRestartButton() {
        Button restartButton = new Button();
        restartButton.setIcon(VaadinIcon.REFRESH.create());
        restartButton.addClickListener(click -> {
            createPlayGround();
        });
        add(restartButton);
    }

    private void setupGrid() {
        mainLayout = new VerticalLayout();
        mainLayout.addClassName("play-layout");
        mainLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(mainLayout);
    }

    private void createPlayGround() {
        mainLayout.removeAll();

        groundWidth = 10;
        groundHeight = 10;

        allTiles = new Tile[groundHeight][groundWidth];

        Random random = new Random();
        for (int i = 0; i < groundWidth; i++) {
            HorizontalLayout row = new HorizontalLayout();
            for (int j = 0; j < groundHeight; j++) {
                Tile button = new Tile();
                button.addClickListener(clickEvent -> {
                    if (button.isBomb) {
                        showAllGround();
                        Notification.show("Game over!");
                    } else {
                        button.setText(Integer.toString(countBombsAround(button)));
                        button.setEnabled(false);
                    }
                });
                button.x = j;
                button.y = i;

                button.isBomb = random.nextInt(100) < 20 ? true : false;
                button.addClassName("tile");
                row.add(button);

                allTiles[i][j] = button;
            }
            mainLayout.add(row);
            mainLayout.setHorizontalComponentAlignment(Alignment.CENTER, row);
        }

    }

    private void showAllGround() {
        for (int i = 0; i < groundHeight; i++) {
            for (int j = 0; j < groundWidth; j++) {
                if (allTiles[i][j].isBomb) {
                    allTiles[i][j].setText(BOMB_ICON);
                } else {
                    allTiles[i][j].setText(Integer.toString(countBombsAround(allTiles[i][j])));
                }
            }
        }
    }

    private int countBombsAround(Tile button) {
        int bombsCount = 0;
        for (int i = button.y - 1; i <= button.y + 1; i++) {
            for (int j = button.x - 1; j <= button.x + 1; j++) {
                if (i < 0 || j < 0 || i > groundHeight - 1 || j > groundWidth - 1) {
                    continue;
                }
                if (allTiles[i][j].isBomb) {
                    bombsCount++;
                }
            }
        }
        return bombsCount;
    }
}
