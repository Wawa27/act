import models.Board;
import strategies.ConfigurationEvaluatorStrategy;
import strategies.RecursiveConfigurationEvaluator;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner;
        if (args.length == 0) {
            scanner = new Scanner(System.in);
        } else {
            scanner = new Scanner(args[1]);
        }

        int height = scanner.nextInt();
        scanner.nextLine();
        int width = scanner.nextInt();
        scanner.nextLine();

        char[][] cells = new char[height][width];
        for (int y = 0; y < height; y++) {
            String pawns = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                cells[y][x] = pawns.charAt(x);
            }
        }

        Board board = new Board(cells, 'P');
        System.out.println("board = " + board);

        ArrayList<ConfigurationEvaluatorStrategy> strategies = new ArrayList<>();
        strategies.add(new RecursiveConfigurationEvaluator());

        for (ConfigurationEvaluatorStrategy strategy : strategies) {
            System.out.println("strategy = " + strategy.getClass().getSimpleName());
            System.out.println("strategy = " + strategy.evaluate(board));
        }
    }
}