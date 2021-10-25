package edu.phystech;

enum CheckerColor {
    WHITE,
    BLACK,
    NONE;

    CheckerColor checkOppositeColor() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return NONE;
    }
}
