package edu.phystech;

import lombok.Getter;

public final class Checker {
    @Getter private CheckerColor color;
    private boolean dame;

    Checker(CheckerColor color) {
        this.color = color;
        dame = false;
    }

    Checker(CheckerColor color, boolean dame) {
        this.color = color;
        this.dame = dame;
    }

    boolean isDame() {
        return dame;
    }

    boolean isNone() {
        return color == CheckerColor.NONE;
    }

    void makeDame() {
        dame = true;
    }
}
