package edu.phystech;

import org.assertj.core.api.BooleanAssert;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import java.util.ArrayList;
import java.util.Arrays;

public class TestExample {

    ArrayList<String> gamePlot = new ArrayList<>(Arrays.asList("a1 a3 b2 c1 c3 d2 e1 e3 f2 g1 g3 h2",
                                                               "a7 b6 b8 c7 d6 d8 e7 f6 f8 g7 h6 h8",
                                                                "g3-f4 f6-e5",
                                                                "c3-d4 e5:c3",
                                                                "b2:d4 d6-c5",
                                                                "d2-c3 g7-f6",
                                                                "h2-g3 h8-g7",
                                                                "c1-b2 f6-g5",
                                                                "g3-h4 g7-f6",
                                                                "f4-e5 f8-g7"));

    ArrayList<String> gameResult = new ArrayList<>(Arrays.asList("a1 a3 b2 c3 d4 e1 e3 e5 f2 g1 h4",
                                                                 "a7 b6 b8 c5 c7 d8 e7 f6 g5 g7 h6"));

    @Test
    void test1() {
        Game game = new Game(gamePlot);

        try {
            game.run();
        } catch (IllegalMoveException e) {
            System.out.println(e.getMessage());
            return;
        }

        int i = 0;
        for (ArrayList<String> colorPos : game.getPos()) {
            String listString = String.join(" ", colorPos);
            Assertions.assertThat(listString).isEqualTo(gameResult.get(i));
            i++;
        }
    }

}

