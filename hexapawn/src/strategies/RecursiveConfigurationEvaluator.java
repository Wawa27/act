package strategies;

import models.Board;

public class RecursiveConfigurationEvaluator implements ConfigurationEvaluatorStrategy {

    public RecursiveConfigurationEvaluator() {
    }

    @Override
    public int evaluate(Board board) {
        for (int y = 0; y < board.getCells().length; y++) {
            for (int x = 0; x < board.getRow(y).length; x++) {
                char currentPlayerCharacter = board.getCurrentPlayerCharacter();
                char otherPlayerCharacter = currentPlayerCharacter == 'P' ? 'p' : 'P';
                int yLookUp = y + currentPlayerCharacter == 'P' ? -1 : 1;

                if (board.getCell(x, y) == currentPlayerCharacter) {
                    // Borders safety
                    int minXLookUp = Math.min(x - 1, 0);
                    int maxXLookUp = Math.max(x + 1, board.getRow(y).length);

                    for (int xLookUp = minXLookUp; xLookUp < maxXLookUp; xLookUp++) {
                        // Only allow diagonal captures or forward move
                        if (board.getCell(xLookUp, yLookUp) == otherPlayerCharacter &&
                                xLookUp != x || board.getCell(x, y) == ' ' && xLookUp == x) {
                            if (currentPlayerCharacter == 'P' && yLookUp == 0) {
                                return 1;
                            } else if (currentPlayerCharacter == 'p' && yLookUp == board.getCells().length - 1) {
                                return -1;
                            }
                            Board bordWithNewMove = new Board(board.getCells());
                            bordWithNewMove.movePawn(x, y, xLookUp, yLookUp);

                            int evaluation = evaluate(board) + Math.copySign() 1;
                        }
                    }
                }
            }
        }
    }
}
