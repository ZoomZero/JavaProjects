package edu.phystech;

import lombok.Getter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;

public final class Board {
    @Getter private final int horisontalNum = 8;
    @Getter private final int verticalNum = 8;
    private ArrayList<ArrayList<Cell>> board = new ArrayList<>(horisontalNum);
    @Getter private HashMap<CheckerColor, HashSet<CheckerPos>> checkerPoses = new HashMap<>();

    Board(ArrayList<String> whitePos, ArrayList<String> blackPos) {
        for (int i = 0; i < verticalNum; i++) {
            board.add(new ArrayList<>(horisontalNum));
            for (int j = 0; j < horisontalNum; j++) {
                board.get(i).add(new Cell(i, j));
            }
        }

        checkerPoses.put(CheckerColor.WHITE, new HashSet<>());
        checkerPoses.put(CheckerColor.BLACK, new HashSet<>());
        //checkerPoses.put(CheckerColor.NONE, new HashSet<>());

        initPos(whitePos, CheckerColor.WHITE);
        initPos(blackPos, CheckerColor.BLACK);
    }

    private void initPos(ArrayList<String> pos, CheckerColor color) {
        pos.forEach(coords -> placeChecker(coords, color));
    }

    private void placeChecker(String coords, CheckerColor color) {
        Coordinates coord = new Coordinates(coords, color);
        board.get(coord.getVerticalPos()).get(coord.getHorisontalPos()).placeChecker(color, coord.isDame());
        CheckerPos checkerPos = new CheckerPos(coord.getVerticalPos(), coord.getHorisontalPos());
        checkerPoses.get(color).add(checkerPos);
    }

    boolean cellIsEmpty(Coordinates coord) {
        return board.get(coord.getVerticalPos()).get(coord.getHorisontalPos()).isEmpty();
    }

    private Checker getChecker(int verticalNumber, int horisontalNumber) {
        return board.get(verticalNumber).get(horisontalNumber).getChecker();
    }

    Checker getCheckerByCoords(Coordinates coord) {
        return board.get(coord.getVerticalPos()).get(coord.getHorisontalPos()).getChecker();
    }

    private Cell getCell(int verticalNumber, int horisontalNumber) {
        return board.get(verticalNumber).get(horisontalNumber);
    }

    void moveChecker(Coordinates from, Coordinates to) {
        Cell toCell = getCell(to.getVerticalPos(), to.getHorisontalPos());
        toCell.placeChecker(getChecker(from.getVerticalPos(), from.getHorisontalPos()));

        checkerPoses.get(toCell.getChecker().getColor()).
                add(new CheckerPos(to.getVerticalPos(), to.getHorisontalPos()));
        removeChecker(from);
    }

    void removeChecker(Coordinates coords) {
        Cell cell = board.get(coords.getVerticalPos()).get(coords.getHorisontalPos());
        checkerPoses.get(cell.getChecker().getColor()).removeIf(checkerPos -> coords.getVerticalPos()
                == checkerPos.getVerticalPos() && coords.getHorisontalPos() == checkerPos.getHorisontalPos());
        cell.deleteChecker();
    }

    ArrayList<ArrayList<String>> showPos() {
        ArrayList<ArrayList<String>> posNotation = new ArrayList<>();
        posNotation.add(new ArrayList<>());
        posNotation.add(new ArrayList<>());

        for (int i = 0; i < verticalNum; i++) {
            for (int j = 0; j < horisontalNum; j++) {
                if (getChecker(i, j).getColor() == CheckerColor.WHITE) {
                    posNotation.get(0).add(getNotation(i, j));
                } else if (getChecker(i, j).getColor() == CheckerColor.BLACK) {
                    posNotation.get(1).add(getNotation(i, j));
                }
            }
        }
        Collections.sort(posNotation.get(0));
        Collections.sort(posNotation.get(1));
        return posNotation;
    }

    private String getNotation(int verticalNumber, int horisontalNumber) {
        String letter = Character.toString((char) (getChecker(verticalNumber, horisontalNumber).isDame()
                ? 'A' + horisontalNumber : 'a' + horisontalNumber));
        return letter + (verticalNumber + 1);
    }
}

