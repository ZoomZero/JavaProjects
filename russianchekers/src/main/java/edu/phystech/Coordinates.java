package edu.phystech;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

public final class Coordinates {
    @Getter private int horisontalPos;
    @Getter private int verticalPos;
    @Getter private CheckerColor checkerColor;
    @Getter private CellColor cellColor;
    @Setter private boolean dame;
    private final char lowerCaseA = 'a';
    private final char upperCaseA = 'A';

    Coordinates(String coords, CheckerColor checkerColor) {
        this.checkerColor = checkerColor;
        verticalPos = Integer.parseInt(Character.toString(coords.charAt(1))) - 1;
        char horizontalPosLetter = coords.charAt(0);

        if (Character.isLowerCase(horizontalPosLetter)) {
            horisontalPos = horizontalPosLetter - lowerCaseA;
            dame = false;
        } else {
            horisontalPos = horizontalPosLetter - upperCaseA;
            dame = true;
        }

        cellColor = (horisontalPos + verticalPos) % 2 == 0 ? CellColor.BLACK : CellColor.WHITE;
    }

    Coordinates(int verticalPos, int horisontalPos, CheckerColor checkerColor) {
        this.horisontalPos = horisontalPos;
        this.verticalPos = verticalPos;
        this.checkerColor = checkerColor;
    }

    boolean canReach(Coordinates cell) {
        return this.isDame() ? sameDiag(cell) : isNeighbour(cell);
    }

    private boolean isNeighbour(Coordinates cell) {
        return Math.abs(cell.horisontalPos - this.horisontalPos) == 1
                && Math.abs(cell.verticalPos - this.verticalPos) == 1;
    }

    private boolean sameDiag(Coordinates cell) {
        return Math.abs(cell.verticalPos - this.verticalPos) == Math.abs(cell.horisontalPos - this.horisontalPos);
    }

    boolean isDame() {
        return dame;
    }

    ArrayList<Coordinates> calculateRouteTo(Coordinates to) throws InvalidCoordinatesException {
        if (!this.sameDiag(to)) {
            throw new InvalidCoordinatesException("different diagonals");
        } else {
            int horDiff = (int) Math.signum(to.horisontalPos - this.horisontalPos);
            int verDiff = (int) Math.signum(to.verticalPos - this.verticalPos);
            int curHorPos = this.horisontalPos;
            int curVerPos = this.verticalPos;
            ArrayList<Coordinates> route = new ArrayList<>();

            while (curHorPos != to.horisontalPos || curVerPos != to.verticalPos) {
                curHorPos += horDiff;
                curVerPos += verDiff;
                route.add(new Coordinates(curVerPos, curHorPos, CheckerColor.NONE));
            }

            return route;
        }
    }

    boolean isWhite() {
        return cellColor == CellColor.WHITE;
    }

    boolean outOfBoard(Board board) {
        return horisontalPos < 0 || horisontalPos >= board.getHorisontalNum()
                || verticalPos < 0 || verticalPos >= board.getVerticalNum();
    }

    boolean becomeDame(Board board) {
        return checkerColor == CheckerColor.WHITE && verticalPos == board.getVerticalNum() - 1
                || checkerColor == CheckerColor.BLACK && verticalPos == 0;
    }
}
