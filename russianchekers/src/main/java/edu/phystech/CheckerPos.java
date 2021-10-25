package edu.phystech;

import lombok.Getter;

public class CheckerPos {
    @Getter private int horisontalPos;
    @Getter private int verticalPos;

    CheckerPos(int verticalPos, int horisontalPos) {
        this.horisontalPos = horisontalPos;
        this.verticalPos = verticalPos;
    }
}
