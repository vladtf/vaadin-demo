package com.live.vladislav.ui.views;

import com.live.vladislav.ui.views.minesweeper.Tile;
import com.vaadin.componentfactory.ToggleButton;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

import javax.annotation.PostConstruct;
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
    private int groundWidth = 10;
    private int groundHeight = 10;
    private int bombsCount;
    private int bombsPercent = 10;
    private VerticalLayout playGroundLayout;
    private ToggleButton flagToggle;
    private Label bombsLabel;

    public MineSweeperView() {
        setupGrid();
        addFlagSwitch();
        addBombsLabel();
        createPlayGround();
        addRestartButton();
    }

    @PostConstruct
    private void init(){
        UI.getCurrent().addShortcutListener(fireEvent->toggleFlag(), Key.KEY_F);
    }

    private void addBombsLabel() {
        bombsLabel = new Label();
        bombsLabel.addClassName("bombs-label");
        mainLayout.addComponentAsFirst(bombsLabel);
    }

    private void addFlagSwitch() {
        flagToggle = new ToggleButton(" ' f ' "+FLAG_ICON);
        flagToggle.setValue(false);

        mainLayout.add(flagToggle);
    }

    private void toggleFlag() {
        if(flagToggle.getValue()){
            Notification.show("Toggled flags : OFF");
        }else{
            Notification.show("Toggled flags : ON");
        }

        flagToggle.setValue(!flagToggle.getValue());
    }

    private void addRestartButton() {
        Button restartButton = new Button();
        restartButton.setIcon(VaadinIcon.REFRESH.create());
        restartButton.addClickListener(click -> {
            createPlayGround();
            bombsLabel.setText(Integer.toString(bombsCount));
        });
        mainLayout.add(restartButton);
    }

    private void setupGrid() {
        mainLayout = new VerticalLayout();
        mainLayout.addClassName("play-layout");
        mainLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(mainLayout);
    }

    private void createPlayGround() {
        if (playGroundLayout == null) {
            playGroundLayout = new VerticalLayout();
            mainLayout.add(playGroundLayout);
        } else {
            playGroundLayout.removeAll();
        }

        bombsCount = ((groundHeight * groundWidth * bombsPercent) / 100);

        allTiles = new Tile[groundHeight][groundWidth];

        Random random = new Random();

        int currentBombs = 0;
        for (int i = 0; i < groundWidth; i++) {
            HorizontalLayout row = new HorizontalLayout();
            for (int j = 0; j < groundHeight; j++) {
                Tile button = new Tile();
                button.addClickListener(clickEvent -> {
                    if (flagToggle.getValue()) {
                        if (button.getText().equals(EMPTY_ICON)) {
                            button.setText(FLAG_ICON);
                            bombsLabel.setText(Integer.toString(Integer.parseInt(bombsLabel.getText()) - 1));
                        }
                        return;
                    }
                    if (button.getText().equals(FLAG_ICON)) {
                        button.setText(EMPTY_ICON);
                        bombsLabel.setText(Integer.toString(Integer.parseInt(bombsLabel.getText()) + 1));
                    } else {
                        if (button.isBomb) {
                            showAllGround();
                            Notification.show("Game over!");
                        } else {
                            showTile(button);
                        }
                    }
                });
                button.x = j;
                button.y = i;

                if (currentBombs < bombsCount) {
                    if (random.nextInt(100) < bombsPercent) {
                        button.isBomb = true;
                        currentBombs++;
                    }
                }

                button.addClassName("tile");
                row.add(button);

                allTiles[i][j] = button;
            }
            playGroundLayout.add(row);
            playGroundLayout.setHorizontalComponentAlignment(Alignment.CENTER, row);
        }
        bombsLabel.setText(Integer.toString(currentBombs));
    }

    private void showTile(Tile button) {
        if (!button.isEnabled()) {
            return;
        }

        int bombsAround = countBombsAround(button);

        button.setEnabled(false);
        button.addClassName("uncovered-tile");

        if (bombsAround == 0) {
            for (int i = button.y - 1; i <= button.y + 1; i++) {
                for (int j = button.x - 1; j <= button.x + 1; j++) {
                    if (i < 0 || j < 0 || i > groundHeight - 1 || j > groundWidth - 1) {
                        continue;
                    }
                    showTile(allTiles[i][j]);
                }
            }
        } else {
            button.setText(Integer.toString(bombsAround));
        }

        checkWin();
    }

    private void checkWin() {
//        for (int i = 0; i < groundHeight; i++) {
//            for (int j = 0; j < allTiles.length; j++) {
//                Tile[] allTile = allTiles[j];
//
//            }
//            Tile[] tiles = groundHeight[i];
//
//        }
        boolean won = true;

        for (int i = 0; i < groundHeight; i++) {
            for (int j = 0; j < groundWidth; j++) {
                if (allTiles[i][j].isBomb)
                    if (!allTiles[i][j].getText().equals(FLAG_ICON)) {
                        won=false;
                        break;
                    }
            }
        }
        if (won) {
            Notification.show("You won!");
            showAllGround();
        }
    }

    private void showAllGround() {
        for (int i = 0; i < groundHeight; i++) {
            for (int j = 0; j < groundWidth; j++) {
                if (allTiles[i][j].isBomb) {
                    if (allTiles[i][j].getText().equals(FLAG_ICON)) {
                        allTiles[i][j].addClassName("green-tile");
                    } else {
                        allTiles[i][j].addClassName("red-tile");
                    }
                    allTiles[i][j].setText(BOMB_ICON);
                } else {
                    //allTiles[i][j].setText(Integer.toString(countBombsAround(allTiles[i][j])));
                    showTile(allTiles[i][j]);
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
