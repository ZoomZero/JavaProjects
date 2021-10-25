package edu.phystech;

import java.util.ArrayList;
import java.util.HashSet;

public final class MoveActivist {
    private Board board;
    private Judge judge = new Judge();

    MoveActivist(Board board) {
        this.board = board;
    }

    void playMove(Move move) throws IllegalMoveException {
        judge.commonCheck(move, board);

        if (move.getType() == MoveType.STEP) {
            playStepMove(move);
        } else {
            playTakeMove(move);
        }
    }

    private void playStepMove(Move move) throws IllegalMoveException {
        judge.stepMoveCheck(move, board);

        ArrayList<Coordinates> movePair = move.getCoordsSequence();
        board.moveChecker(movePair.get(0), movePair.get(1));

        if (becomesDame(move)) {
            board.getCheckerByCoords(movePair.get(1)).makeDame();
        }
    }

    private void playTakeMove(Move move) throws IllegalMoveException {
        HashSet<Coordinates> takenCheckers = judge.takeMoveCheck(move, board);

        ArrayList<Coordinates> moveCoordsSequence = move.getCoordsSequence();
        board.moveChecker(moveCoordsSequence.get(0), moveCoordsSequence.get(moveCoordsSequence.size() - 1));
        takenCheckers.forEach(coords -> board.removeChecker(coords));

        if (becomesDame(move)) {
            board.getCheckerByCoords(moveCoordsSequence.get(moveCoordsSequence.size() - 1)).makeDame();
        }
    }

    private boolean becomesDame(Move move) {
        return move.getCoordsSequence().stream().anyMatch(coords -> coords.becomeDame(board));
    }
}
