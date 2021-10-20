package strategies;

import models.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CachedRecursiveConfigurationEvaluator implements ConfigurationEvaluatorStrategy {

    public CachedRecursiveConfigurationEvaluator() {
    }

    @Override
    public int evaluate(Board board) {
        return evaluateWithCache(board, new HashMap<>(), new HashMap<>());
    }

    private int evaluateWithCache(Board board, Map<String, Integer> whitePlayerCache, Map<String, Integer> blackPlayerCache) {
        // Exit conditions, first check in cache before any high cost checks
        if (board.getCurrentPlayerCharacter() == 'P' && whitePlayerCache.get(board.toString()) != null) {
            return whitePlayerCache.get(board.toString());
        } else if (board.getCurrentPlayerCharacter() == 'p' && blackPlayerCache.get(board.toString()) != null) {
            return blackPlayerCache.get(board.toString());
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

        int configurationValue = computeConfigurationValue(successors);

        // Cache results
        // Make a Y mirrored version of the board and 
        if (board.getCurrentPlayerCharacter() == 'P') {
            whitePlayerCache.put(board.toString(), configurationValue);
            whitePlayerCache.put(board.toStringYAxisMirrored(), configurationValue);
        } else {
            blackPlayerCache.put(board.toString(), configurationValue);
            blackPlayerCache.put(board.toStringYAxisMirrored(), configurationValue);
        }
        return configurationValue;
    }
}
