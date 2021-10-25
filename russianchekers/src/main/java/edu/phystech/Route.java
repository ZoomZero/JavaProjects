package edu.phystech;

import java.util.ArrayList;
import java.util.HashSet;

public final class Route {
    private Coordinates to;
    private Coordinates from;

    Route(Coordinates from, Coordinates to) {
        this.from = from;
        this.to = to;
    }

    boolean canStep(Board board) {
        return from.canReach(to) && isFree(board);
    }

    private boolean isFree(Board board) {
        ArrayList<Coordinates> curRoute;

        try {
            curRoute = from.calculateRouteTo(to);
        } catch (InvalidCoordinatesException e) {
            return false;
        }

        return curRoute.stream().allMatch(board::cellIsEmpty);
    }

    Coordinates canTake(Board board, HashSet<Coordinates> takenPieces) throws IllegalMoveException {
        ArrayList<Coordinates> curRoute;

        try {
            curRoute = from.calculateRouteTo(to);
        } catch (InvalidCoordinatesException e) {
            throw new IllegalMoveException("error4");
        }

        if (!board.getCheckerByCoords(curRoute.get((curRoute.size()) - 1)).isNone()) {
            throw new IllegalMoveException("busy cell");
        }

        CheckerColor checkerColor = from.getCheckerColor();
        long routeCount =  curRoute.stream().filter(coords -> board.getCheckerByCoords(coords).getColor()
                == checkerColor.checkOppositeColor()).count();

        if (routeCount > 1) {
            throw new IllegalMoveException("error5");
        }

        if (curRoute.stream().anyMatch(coords -> board.getCheckerByCoords(coords).getColor()
                == checkerColor)) {
            throw new IllegalMoveException("error6");
        }

        for (Coordinates coords : curRoute) {
            if (board.getCheckerByCoords(coords).getColor() == checkerColor.checkOppositeColor()) {
                return coords;
            }
        }

        throw new IllegalMoveException("error7");
    }
}
