package models;

import java.util.Arrays;

public class Board {
    private char[][] cells;
    private final char currentPlayerCharacter;

    public Board(char[][] cells) {
        this(cells, 'P');
    }

    public Board(char[][] cells, char playerTurn) {
        this.cells = cells.clone();
        this.currentPlayerCharacter = playerTurn;
    }

    public void movePawn(int sourceX, int sourceY, int destinationX, int destinationY) {
        this.cells[destinationY][destinationX] = this.cells[sourceY][sourceX];
        this.cells[sourceY][sourceX] = ' ';
    }

    /**
     * Check if the current player can win by moving a pawn
     *
     * @return True if current player can win, false otherwise
     */
    public boolean canCurrentPlayerWin() {
        if (this.currentPlayerCharacter == 'p') {
            int beforeLastRow = this.cells.length - 2;
            for (int i = 0; i < this.cells[beforeLastRow].length; i++) {
                if (this.isAllowedMove(i, beforeLastRow, i, beforeLastRow + 1)) {
                    return true;
                } else if (this.isAllowedMove(i, beforeLastRow, i - 1, beforeLastRow + 1)) {
                    return true;
                } else if (this.isAllowedMove(i, beforeLastRow, i + 1, beforeLastRow + 1)) {
                    return true;
                }
            }
        } else if (this.currentPlayerCharacter == 'P') {
            for (int i = 0; i < this.cells[1].length; i++) {
                if (this.isAllowedMove(i, 1, i, 0)) {
                    return true;
                } else if (this.isAllowedMove(i, 1, i - 1, 0)) {
                    return true;
                } else if (this.isAllowedMove(i, 1, i + 1, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public int getCurrentPlayerDestination() {
        return this.currentPlayerCharacter == 'P' ? -1 : 1;
    }

    public char getCurrentPlayerCharacter() {
        return currentPlayerCharacter;
    }

    public char getOtherPlayerCharacter() {
        return currentPlayerCharacter == 'P' ? 'p' : 'P';
    }

    public boolean isAllowedMove(int sourceX, int sourceY, int destinationX, int destinationY) {
        if (destinationY < 0 || destinationY >= this.cells.length) {
            return false;
        } else if (destinationX < 0 || destinationX >= this.cells[destinationY].length) {
            return false;
        }
        return this.cells[sourceY][sourceX] == this.getCurrentPlayerCharacter() &&
                (sourceX != destinationX && this.cells[destinationY][destinationX] == getOtherPlayerCharacter()
                        || sourceX == destinationX && this.cells[destinationY][destinationX] == ' ');
    }

    public char getCell(int x, int y) {
        return this.cells[y][x];
    }

    public char[] getRow(int y) {
        return this.cells[y];
    }

    public char[][] getCells() {
        return cells;
    }

    public void setCells(char[][] cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] row : cells) {
            stringBuilder.append(row);
        }
        return stringBuilder.toString();
    }

    public String toStringYAxisMirrored() {
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] row : cells) {
            StringBuilder reverse = new StringBuilder().append(row).reverse();
            stringBuilder.append(reverse);
        }
        return stringBuilder.toString();
    }

    public String toStringXAxisMirrored() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = cells.length - 1; i >= 0; i--) {
            stringBuilder.append(cells[i]);
        }
        return stringBuilder.toString();
    }

    public String toStringXYAxisMirrored() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder reverseBuilder = new StringBuilder();
        for (int i = cells.length - 1; i >= 0; i--) {
            stringBuilder.append(reverseBuilder.insert(0, cells[i]).reverse());
        }
        return stringBuilder.toString();
    }
}
