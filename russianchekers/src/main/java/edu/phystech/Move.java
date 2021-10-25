package edu.phystech;

import lombok.Getter;
import java.util.ArrayList;
import java.util.Arrays;

public final class Move {
    @Getter private CheckerColor checkerColor;
    private boolean dame;
    @Getter private MoveType type;
    @Getter private ArrayList<Coordinates> coordsSequence = new ArrayList<>();

    Move(String move, CheckerColor checkerColor) {
        this.checkerColor = checkerColor;

        ArrayList<String> coordsChange = new ArrayList<>(Arrays.asList(move.split("-")));

        if (coordsChange.size() > 1) {
            type = MoveType.STEP;
        } else {
            coordsChange = new ArrayList<>(Arrays.asList(move.split(":")));
            type = MoveType.TAKE;
        }

        coordsChange.forEach(coords -> coordsSequence.add(new Coordinates(coords, this.checkerColor)));

        dame = coordsSequence.get(0).isDame();
        coordsSequence.forEach(coords -> coords.setDame(coordsSequence.get(0).isDame()));
    }

    boolean isDame() {
        return dame;
    }
}
