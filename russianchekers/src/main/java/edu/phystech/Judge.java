package edu.phystech;

import java.util.ArrayList;
import java.util.HashSet;

public final class Judge {

    void commonCheck(Move move, Board board) throws IllegalMoveException {
        noWhiteCells(move);
        pieceColorMatches(move, board);
        isDameMatches(move, board);
    }

    private void noWhiteCells(Move move) throws IllegalMoveException {
        if (move.getCoordsSequence().stream().anyMatch(Coordinates::isWhite)) {
            throw new IllegalMoveException("white cell");
        }
    }

    private void pieceColorMatches(Move move, Board board) throws IllegalMoveException {
        if (!(move.getCheckerColor() == board.getCheckerByCoords(move.getCoordsSequence().get(0)).getColor())) {
            throw new IllegalMoveException("error1");
        }
    }

    private void isDameMatches(Move move, Board board) throws IllegalMoveException {
        if (!(move.isDame() == board.getCheckerByCoords(move.getCoordsSequence().get(0)).isDame())) {
            throw new IllegalMoveException("error2");
        }
    }

    void stepMoveCheck(Move move, Board board) throws IllegalMoveException {
        targetCellEmpty(move, board);
        ArrayList<Coordinates> coordsPair = move.getCoordsSequence();
        if (!(new Route(coordsPair.get(0), coordsPair.get(1))).canStep(board)) {
            throw new IllegalMoveException("error3");
        }
        noneCanTake(move, board);
    }

    private void targetCellEmpty(Move move, Board board) throws IllegalMoveException {
        if (!board.cellIsEmpty(move.getCoordsSequence().get(1))) {
            throw new IllegalMoveException("busy cell");
        }
    }

    private void noneCanTake(Move move, Board board) throws IllegalMoveException {
        for (CheckerPos piecePosition : board.getCheckerPoses().get(move.getCheckerColor())) {
            canNotTake(
                    new Coordinates(piecePosition.getVerticalPos(), piecePosition.getHorisontalPos(),
                            move.getCheckerColor()),
                            board,
                            new HashSet<>()
            );
        }
    }

    private void canNotTake(Coordinates coords, Board board, HashSet<Coordinates> takenPieces)
            throws IllegalMoveException {
        if (canTakeDirection(coords, board, -1, -1, takenPieces)) {
            throw new IllegalMoveException("invalid move");
        } else if (canTakeDirection(coords, board, -1, 1, takenPieces)) {
            throw new IllegalMoveException("invalid move");
        } else if (canTakeDirection(coords, board, 1, -1, takenPieces)) {
            throw new IllegalMoveException("invalid move");
        } else if (canTakeDirection(coords, board, 1, 1, takenPieces)) {
            throw new IllegalMoveException("invalid move");
        }
    }

    private boolean canTakeDirection(Coordinates coords, Board board, int rankDelta, int fileDelta,
                                     HashSet<Coordinates> takenPieces) {
        if (!coords.isDame()) {
            return !takenPieces.contains(coords) && canTakeDirectionNotDame(coords, board, rankDelta, fileDelta);
        } else {
            return !takenPieces.contains(coords) && canTakeDirectionDame(coords, board, rankDelta, fileDelta);
        }
    }

    private boolean canTakeDirectionNotDame(Coordinates coords, Board board, int rankDelta, int fileDelta) {
        Coordinates takeCoords = new Coordinates(
                coords.getVerticalPos() + rankDelta,
                coords.getHorisontalPos() + fileDelta,
                board.getCheckerByCoords(coords).getColor().checkOppositeColor()
        );
        Coordinates landCoords = new Coordinates(
                coords.getVerticalPos() + 2 * rankDelta,
                coords.getHorisontalPos() + 2 * fileDelta,
                CheckerColor.NONE
        );

        if (takeCoords.outOfBoard(board) || landCoords.outOfBoard(board)) {
            return false;
        }

        return takeCoords.getCheckerColor() == board.getCheckerByCoords(takeCoords).getColor()
                && landCoords.getCheckerColor() == board.getCheckerByCoords(landCoords).getColor();
    }

    private boolean canTakeDirectionDame(Coordinates pieceCoords, Board board, int rankDelta, int fileDelta) {
        CheckerColor pieceColor = board.getCheckerByCoords(pieceCoords).getColor();
        Coordinates curCoords = new Coordinates(
                pieceCoords.getVerticalPos() + rankDelta,
                pieceCoords.getHorisontalPos() + fileDelta,
                pieceColor.checkOppositeColor()
        );
        boolean legal = true;
        int counter = 0;
        if (curCoords.outOfBoard(board) || board.getCheckerByCoords(curCoords).getColor() == pieceColor) {
            legal = false;
        }
        while (legal && board.getCheckerByCoords(curCoords).getColor() != pieceColor.checkOppositeColor()) {
            ++counter;
            curCoords = new Coordinates(
                    pieceCoords.getVerticalPos() + counter * rankDelta,
                    pieceCoords.getHorisontalPos() + counter * fileDelta,
                    pieceColor.checkOppositeColor()
            );
            if (curCoords.outOfBoard(board) || board.getCheckerByCoords(curCoords).getColor() == pieceColor) {
                legal = false;
                break;
            }
        }
        ++counter;
        Coordinates landingCell = new Coordinates(
                pieceCoords.getVerticalPos() + counter * rankDelta,
                pieceCoords.getHorisontalPos() + counter * fileDelta,
                CheckerColor.NONE
        );
        if (landingCell.outOfBoard(board)) {
            legal = false;
        }
        return  legal && board.getCheckerByCoords(landingCell).getColor() == CheckerColor.NONE;
    }

    HashSet<Coordinates> takeMoveCheck(Move move, Board board) throws IllegalMoveException {
        ArrayList<Coordinates> coordsSequence = move.getCoordsSequence();
        HashSet<Coordinates> takenPieces = new HashSet<>();
        for (int i = 0; i < coordsSequence.size() - 1; ++i) {
            Route route = new Route(coordsSequence.get(i), coordsSequence.get(i + 1));
            takenPieces.add(route.canTake(board, takenPieces));
        }

        return takenPieces;
    }
}
