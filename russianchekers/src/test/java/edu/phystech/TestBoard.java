package edu.phystech;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestBoard {
    private String white = "a1 a3 b2 c1 c3 d2 e1 e3 f2 g1 g3 h2";
    private String black = "a7 b6 b8 c7 d6 d8 e7 f6 f8 g7 h6 h8";
    ArrayList<String> whitePos = new ArrayList<>(Arrays.asList(white.split(" ")));
    ArrayList<String> blackPos = new ArrayList<>(Arrays.asList(black.split(" ")));


    @Test
    void test2() {
        Board board = new Board(whitePos, blackPos);
        Assertions.assertThat(board.showPos()).isEqualTo(new ArrayList<>(Arrays.asList(whitePos, blackPos)));
    }
}
