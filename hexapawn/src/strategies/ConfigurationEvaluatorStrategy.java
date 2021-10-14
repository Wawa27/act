package strategies;

import models.Board;

import java.util.Collections;
import java.util.List;

public interface ConfigurationEvaluatorStrategy {
    /**
     * Calculate the configuration value from the board's successors
     * Implementation of question°1
     */
    default int computeConfigurationValue(List<Integer> successors) {
        if (Collections.min(successors) > 0) {
            return -1 * (Collections.max(successors) + 1);
        } else {
            // We want to win the fastest as possible, so we take the nearest number to 0
            for (int i = successors.size() - 1; i >= 0; i--) {
                if (successors.get(i) > 0) {
                    successors.remove(i);
                }
            }
            return -1 * (Collections.max(successors) - 1);
        }
    }

    default char[][] cloneCells(char[][] cells) {
        char[][] clonedCells = new char[cells.length][cells[0].length];
        for (int i = 0; i < clonedCells.length; i++) {
            clonedCells[i] = cells[i].clone();
        }

        return clonedCells;
    }

    int evaluate(Board board);
}
