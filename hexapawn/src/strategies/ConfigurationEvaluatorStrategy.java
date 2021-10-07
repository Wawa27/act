package strategies;

import models.Board;

import java.util.Collections;
import java.util.List;

public interface ConfigurationEvaluatorStrategy {
    /**
     * Calculate the configuration value from the board's successors
     * Implementation of question°1
     */
    default int getConfigurationValue(List<Integer> successors) {
        if (Collections.min(successors) > 0) {
            return -1 * (Collections.max(successors) + 1);
        } else {
            return -1 * (Collections.min(successors) + 1);
        }
    }

    int evaluate(Board board);
}
