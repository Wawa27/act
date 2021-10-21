import models.Board;
import org.junit.jupiter.api.Test;
import strategies.CachedRecursiveConfigurationEvaluator;
import strategies.RecursiveConfigurationEvaluator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConfigurationEvaluatorStrategy {

    @Test
    public void testWinningConfiguration() {
        List<Integer> successors = new ArrayList<>();
        successors.add(-8);
        successors.add(4);
        successors.add(-3);

        int configurationValue = new RecursiveConfigurationEvaluator().computeConfigurationValue(successors);

        assertEquals(4, configurationValue);
    }

    @Test
    public void testWinningConfiguration2() {
        List<Integer> successors = new ArrayList<>();
        successors.add(-8);
        successors.add(-4);
        successors.add(-5);

        int configurationValue = new RecursiveConfigurationEvaluator().computeConfigurationValue(successors);

        assertEquals(5, configurationValue);
    }

    @Test
    public void testLosingConfiguration() {
        List<Integer> successors = new ArrayList<>();
        successors.add(1);
        successors.add(4);
        successors.add(9);

        int configurationValue = new RecursiveConfigurationEvaluator().computeConfigurationValue(successors);

        assertEquals(-10, configurationValue);
    }

    @Test
    public void testOtherWinningConfiguration() {
        List<Integer> successors = new ArrayList<>();
        successors.add(1);
        successors.add(2);
        successors.add(7);

        int configurationValue = new RecursiveConfigurationEvaluator().otherConfigurationValue(successors);

        assertEquals(1, configurationValue);
    }

    @Test
    public void testOtherLosingConfiguration() {
        List<Integer> successors = new ArrayList<>();
        successors.add(-1);
        successors.add(-2);
        successors.add(-7);

        int configurationValue = new RecursiveConfigurationEvaluator().otherConfigurationValue(successors);

        assertEquals(-7, configurationValue);
    }

    @Test
    public void testBothConfiguration() {
        Board board = new Board(new char[][]{
                {'p', 'p', 'p'},
                {' ', ' ', ' '},
                {'P', 'P', 'P'}
        });
    }
}
