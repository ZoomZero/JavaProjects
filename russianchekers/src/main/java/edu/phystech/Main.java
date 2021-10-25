package edu.phystech;

import java.util.ArrayList;
import java.util.Scanner;

public final class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> gameplot = new ArrayList<>();

        while (scanner.hasNext()) {
            gameplot.add(scanner.nextLine());
        }

        Game game = new Game(gameplot);

        try {
            game.run();
        } catch (IllegalMoveException e) {
            System.out.println(e.getMessage());
            return;
        }

        for (ArrayList<String> colorPos : game.getPos()) {
            for (String coords : colorPos) {
                System.out.print(coords + " ");
            }
            System.out.println();
        }
    }

    private Main() {

    }
}

//mvn archetype:generate -DarchetypeArtifactId=homework-quickstart
//-DarchetypeGroupId=org.atp-fivt -DarchetypeVersion=1.06 -DgroupId=edu.phystech -DartifactId=russianchekers

//mvn clean verify -e
