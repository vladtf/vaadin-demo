package com.live.vladislav.ui.views.minesweeper;

import com.vaadin.flow.component.html.NativeButton;

public class Tile extends NativeButton {
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
