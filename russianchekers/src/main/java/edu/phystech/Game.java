package edu.phystech;

import java.util.ArrayList;
import java.util.Arrays;

public final class Game {
    private Board board;
    private ArrayList<String> gamePlot;
    private MoveActivist activist;

    Game(ArrayList<String> gamePlot) {
        this.gamePlot = gamePlot;
        ArrayList<String> whitePos = new ArrayList<>(Arrays.asList(this.gamePlot.get(0).split(" ")));
        ArrayList<String> blackPos = new ArrayList<>(Arrays.asList(this.gamePlot.get(1).split(" ")));
        board = new Board(whitePos, blackPos);
        activist = new MoveActivist(board);
    }

    void run() throws IllegalMoveException {
        for (int i = 2; i < gamePlot.size(); i++) {
            ArrayList<String> moves = new ArrayList<>(Arrays.asList(this.gamePlot.get(i).split(" ")));
            Move whiteMove = new Move(moves.get(0), CheckerColor.WHITE);
            Move blackMove = new Move(moves.get(1), CheckerColor.BLACK);
            activist.playMove(whiteMove);
            activist.playMove(blackMove);
        }
    }

    ArrayList<ArrayList<String>> getPos() {
        return board.showPos();
    }
}
