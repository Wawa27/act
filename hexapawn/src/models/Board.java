package models;

public class Board {
    private char[][] cells;
    private char currentPlayerCharacter;

    public Board(char[][] cells) {
        this(cells, 'P');
    }

    public Board(char[][] cells, char playerTurn) {
        this.cells = cells.clone();
        this.currentPlayerCharacter = playerTurn;
    }

    public char getWinner() {
        for (int i = 0; i < 0; i++) {

        }
    }

    public void movePawn(int sourceX, int sourceY, int destinationX, int destinationY) {
        this.cells[destinationY][destinationX] = this.cells[destinationY][destinationX];
        this.cells[sourceY][sourceX] = ' ';
    }

    public char getCurrentPlayerCharacter() {
        return currentPlayerCharacter;
    }

    public void setCurrentPlayerCharacter(char currentPlayerCharacter) {
        this.currentPlayerCharacter = currentPlayerCharacter;
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
}
