package edu.phystech;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TestActivist {

    @Test
    void test1() throws IllegalMoveException {
        ArrayList<String> whitePos = new ArrayList<>(Collections.singletonList("a1"));
        ArrayList<String> blackPos = new ArrayList<>();
        Board board = new Board(whitePos, blackPos);
        Move move = new Move("a1-b2", CheckerColor.WHITE);
        MoveActivist activist = new MoveActivist(board);
        activist.playMove(move);

        Assertions.assertThat(board.showPos()).isEqualTo(new ArrayList<>(
                Arrays.asList(new ArrayList<>(Collections.singletonList("b2")), new ArrayList<>())));
    }

    @Test
    void test2() throws IllegalMoveException {
        ArrayList<String> whitePos = new ArrayList<>(Collections.singletonList("a1"));
        ArrayList<String> blackPos = new ArrayList<>(Collections.singletonList("b2"));
        Board board = new Board(whitePos, blackPos);
        Move move = new Move("a1:c3", CheckerColor.WHITE);
        MoveActivist activist = new MoveActivist(board);
        activist.playMove(move);

        Assertions.assertThat(board.showPos()).isEqualTo(new ArrayList<>(
                Arrays.asList(new ArrayList<>(Collections.singletonList("c3")), new ArrayList<>())));
    }

    @Test
    void test3() throws IllegalMoveException {
        String black = "b2 d4";
        ArrayList<String> whitePos = new ArrayList<>(Collections.singletonList("a1"));
        ArrayList<String> blackPos = new ArrayList<>(Arrays.asList(black.split(" ")));
        Board board = new Board(whitePos, blackPos);
        Move move = new Move("a1:c3:e5", CheckerColor.WHITE);
        MoveActivist activist = new MoveActivist(board);
        activist.playMove(move);

        Assertions.assertThat(board.showPos()).isEqualTo(new ArrayList<>(
                Arrays.asList(new ArrayList<>(Collections.singletonList("e5")), new ArrayList<>())));
    }

    @Test
    void test4() throws IllegalMoveException {
        String white = "b2 d4";
        ArrayList<String> whitePos = new ArrayList<>(Arrays.asList(white.split(" ")));
        ArrayList<String> blackPos = new ArrayList<>(Collections.singletonList("e5"));
        Board board = new Board(whitePos, blackPos);
        Move move = new Move("e5:c3:a1", CheckerColor.BLACK);
        MoveActivist activist = new MoveActivist(board);
        activist.playMove(move);

        Assertions.assertThat(board.showPos()).isEqualTo(new ArrayList<>(
                Arrays.asList(new ArrayList<>(), new ArrayList<>(Collections.singletonList("A1")))));
    }

    @Test
    void test5() throws IllegalMoveException {
        String white = "b2 d6";
        ArrayList<String> whitePos = new ArrayList<>(Arrays.asList(white.split(" ")));
        ArrayList<String> blackPos = new ArrayList<>(Collections.singletonList("E5"));
        Board board = new Board(whitePos, blackPos);
        Move move = new Move("E5:a1", CheckerColor.BLACK);
        MoveActivist activist = new MoveActivist(board);
        activist.playMove(move);

        Assertions.assertThat(board.showPos()).isEqualTo(new ArrayList<>(Arrays.asList(
                new ArrayList<>(Collections.singletonList("d6")),
                new ArrayList<>(Collections.singletonList("A1")))));
    }
}
