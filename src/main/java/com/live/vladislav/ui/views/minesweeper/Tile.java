package com.live.vladislav.ui.views.minesweeper;

import com.vaadin.flow.component.button.Button;

public class Tile extends Button {
    public boolean isBomb;
    public int x;
    public int y;

    public Tile(int x,int y){
        this.x = x;
        this.y = y;
    }

    public Tile(){
    }

}
