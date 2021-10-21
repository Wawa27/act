import models.Board;
import strategies.HardCachedRecursiveConfigurationEvaluator;

import java.util.Scanner;

/**
 * Class used to pass tests on https://contest.fil.univ-lille1.fr/
 */
public class HexapawnContestMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
        System.out.println("strategy = " + new HardCachedRecursiveConfigurationEvaluator().evaluate(board));
    }
}