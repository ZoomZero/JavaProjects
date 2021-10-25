package edu.phystech;

import lombok.Getter;

public final class Cell {
    private int horisontalPos;
    private int verticalPos;
    @Getter private Checker checker;
    private  CheckerColor color;

    Cell(int verticalPos, int horisontalPos) {
        this.horisontalPos = horisontalPos;
        this.verticalPos = verticalPos;
        color = (horisontalPos + verticalPos) % 2 == 0 ? CheckerColor.BLACK : CheckerColor.WHITE;
        checker = new Checker(CheckerColor.NONE);
    }

    void deleteChecker() {
        this.checker = new Checker(CheckerColor.NONE);
    }

    boolean isEmpty() {
        return this.checker.isNone();
    }

    void placeChecker(CheckerColor curColor, boolean dame) {
        this.checker = new Checker(curColor, dame);
    }

    void placeChecker(Checker curChecker) {
        checker = curChecker;
    }
}
