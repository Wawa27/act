package strategies;

import models.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CachedRecursiveConfigurationEvaluator implements ConfigurationEvaluatorStrategy {

    public CachedRecursiveConfigurationEvaluator() {
    }

    @Override
    public int evaluate(Board board) {
        return evaluateWithCache(board, new HashMap<>(), new HashMap<>());
    }

    private int evaluateWithCache(Board board, Map<String, Integer> whitePlayerCache, Map<String, Integer> blackPlayerCache) {
        // Exit conditions, first check in cache before any high cost checks
        String boardString = board.toString();
        if (board.getCurrentPlayerCharacter() == 'P' && whitePlayerCache.get(boardString) != null) {
            return whitePlayerCache.get(boardString);
        } else if (board.getCurrentPlayerCharacter() == 'p' && blackPlayerCache.get(boardString) != null) {
            return blackPlayerCache.get(boardString);
        } else if (board.canCurrentPlayerWin()) {
            return 1;
        }

        ArrayList<Integer> successors = new ArrayList<>();
        // Find all playable pawn
        for (int y = 0; y < board.getCells().length; y++) {
            for (int x = 0; x < board.getRow(y).length; x++) {
                // Restrict to current player's turn's pawns
                if (board.getCell(x, y) == board.getCurrentPlayerCharacter()) {
                    int destinationY = y + board.getCurrentPlayerDestination();

                    // All possible pawn's move (even not allowed ones)
                    for (int destinationX = x - 1; destinationX <= x + 1; destinationX++) {
                        // Only allow diagonal captures or forward move
                        if (board.isAllowedMove(x, y, destinationX, destinationY)) {
                            Board boardWithNewMove = new Board(
                                    cloneCells(board.getCells()),
                                    board.getOtherPlayerCharacter()
                            );
                            boardWithNewMove.movePawn(x, y, destinationX, destinationY);

                            int configurationValue = evaluateWithCache(boardWithNewMove, whitePlayerCache, blackPlayerCache);
                            successors.add(configurationValue);
                        }
                    }
                }
            }
        }

        int currentPlayerConfigurationValue = computeConfigurationValue(successors);

        // Cache results
        if (board.getCurrentPlayerCharacter() == 'P') {
            whitePlayerCache.put(boardString, currentPlayerConfigurationValue);
        } else {
            blackPlayerCache.put(boardString, currentPlayerConfigurationValue);
        }
        return currentPlayerConfigurationValue;
    }
}
