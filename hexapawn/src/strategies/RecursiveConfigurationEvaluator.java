package strategies;

import models.Board;

import java.util.ArrayList;

public class RecursiveConfigurationEvaluator implements ConfigurationEvaluatorStrategy {

    public RecursiveConfigurationEvaluator() {
    }

    @Override
    public int evaluate(Board board) {
        if (board.canCurrentPlayerWin()) {
            return board.getCurrentPlayerCharacter() == 'P' ? -1 : 1;
        }

        ArrayList<Integer> successors = new ArrayList<>();
        // Find all playable pawn
        for (int y = 0; y < board.getCells().length; y++) {
            for (int x = 0; x < board.getRow(y).length; x++) {
                // Restrict to current player's turn's pawns
                if (board.getCell(x, y) == board.getCurrentPlayerCharacter()) {
                    int destinationY = y + (board.getCurrentPlayerCharacter() == 'P' ? -1 : 1);

                    // All possible pawn's move (even not allowed ones)
                    for (int destinationX = x - 1; destinationX < x + 1; destinationX++) {
                        // Only allow diagonal captures or forward move
                        if (board.isAllowedMove(x, y, destinationX, destinationY)) {
                            Board bordWithNewMove = new Board(board.getCells(), board.getOtherPlayerCharacter());
                            bordWithNewMove.movePawn(x, y, destinationX, destinationY);

                            successors.add(evaluate(bordWithNewMove));
                        }
                    }
                }
            }
        }

        return this.getConfigurationValue(successors);
    }
}
