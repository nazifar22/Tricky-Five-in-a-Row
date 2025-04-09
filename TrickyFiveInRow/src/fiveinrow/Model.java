package fiveinrow;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the game model for the Five in a Row game.
 * This class manages the state of the game, including the game board, the current player,
 * and the positions of each player's moves.
 */

public class Model {
    private final int size;
    private Player actualPlayer;
    private Player[][] table;
    public List<Pair> playerOPositions;
    public List<Pair> playerXPositions;
    
    /**
     * Constructs a new game model with a specified size.
     * 
     * @param size The size of the game board.
     */
    
    public Model (int size){
        this.size = size;
        actualPlayer = Player.X;
        playerXPositions = new ArrayList<>();
        playerOPositions = new ArrayList<>();
        initializeBoard();
    }
    
    /**
     * Initializes the game board to the starting state.
     */
    
    private void initializeBoard() {
        table = new Player[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                table[i][j] = Player.NOBODY;
            }
        }
    }
    
    private int numOfAdjUp(int row, int column) {
    int count = 0;
    // Start from the cell above the current cell
    for (int i = row - 1; i >= 0 && table[i][column] == actualPlayer; i--) {
        count++;
    }
    return count;
}
    
    private int numOfAdjBottom(int row, int column) {
    int count = 0;
    // Start checking from the cell below the current cell
    for (int i = row + 1; i < size && table[i][column] == actualPlayer; i++) {
        count++;
    }
    return count;
}
    
    private int numOfAdjRight(int row, int column) {
    int count = 0;
    // Start checking from the cell to the right of the current cell
    for (int col = column + 1; col < size && table[row][col] == actualPlayer; col++) {
        count++;
    }
    return count;
}
    
    private int numOfAdjLeft(int row, int column) {
    int count = 0;
    // Start checking from the cell to the left of the current cell
    for (int col = column - 1; col >= 0 && table[row][col] == actualPlayer; col--) {
        count++;
    }
    return count;
}
    
    private int numOfAdjDiagonal(int row, int column) {
    // Counts for diagonals in both directions
    int countDiagonal1 = countDiagonal(row, column, -1, 1); // upper right to lower left
    int countDiagonal2 = countDiagonal(row, column, 1, -1); // lower left to upper right
    int countDiagonal3 = countDiagonal(row, column, -1, -1); // upper left to lower right
    int countDiagonal4 = countDiagonal(row, column, 1, 1); // lower right to upper left

    // The maximum count from all diagonals
    return Math.max(Math.max(countDiagonal1, countDiagonal2), Math.max(countDiagonal3, countDiagonal4));
}

private int countDiagonal(int row, int column, int rowIncrement, int colIncrement) {
    int count = 0;
    int currentRow = row + rowIncrement;
    int currentCol = column + colIncrement;

    while (currentRow >= 0 && currentRow < size && currentCol >= 0 && currentCol < size
            && table[currentRow][currentCol] == actualPlayer) {
        count++;
        currentRow += rowIncrement;
        currentCol += colIncrement;
    }

    return count;
}

    
    private int[] numOfAdjacentSigns(int row,int column){
        int[] adjacentSigns = new int[]{0,0,0,0,0};// up,right,left,buttom,maxDiagonal
        adjacentSigns[0] = numOfAdjUp(row , column);
        adjacentSigns[1] = numOfAdjRight(row , column);
        adjacentSigns[2] = numOfAdjLeft(row , column);
        adjacentSigns[3] = numOfAdjBottom(row , column);
        adjacentSigns[4] = numOfAdjDiagonal(row , column);
        return adjacentSigns ;
    }
    
    private void removeSignsRandomly(int numberOfSigns) {
        
    List<Pair> playerPositions = actualPlayer == Player.X ? playerXPositions : playerOPositions;
    for (int i = 0; i < numberOfSigns && !playerPositions.isEmpty(); i++) {
        int randomIndex = (int) (Math.random() * playerPositions.size());
        Pair position = playerPositions.remove(randomIndex);
        table[position.x][position.y] = Player.NOBODY;
    }
}
    
    /**
     * Returns a copy of the current game board.
     * 
     * @return A two-dimensional array representing the game board.
     */
    
    public Player[][] getTableCopy() {
        Player[][] copy = new Player[size][size];
        for (int i = 0; i < size; ++i) {
            System.arraycopy(table[i], 0, copy[i], 0, size);
        }
        return copy;
    }
    
    /**
     * Processes a step in the game by a player at the specified row and column.
     * 
     * @param row The row index where the step is made.
     * @param column The column index where the step is made.
     * @return The player who now occupies the specified cell after the step.
     */
    
    public Player step(int row, int column) {
        if (table[row][column] != Player.NOBODY) {
            return table[row][column];
        }

        int[] adjacentSigns = numOfAdjacentSigns(row, column);
        if(!hasWinningCondition(adjacentSigns)){
            handleAdjacentSigns(adjacentSigns);
        }
        
        
        table[row][column] = actualPlayer;
        addPlayerPosition(row, column);
        
        
        Player winner = findWinner(row, column);
        if (winner != Player.NOBODY) {
            // If there's a winner, return the winning player without switching
            return winner;
        }
        
        switchPlayer();
        return table[row][column];
    }

    private void addPlayerPosition(int row, int column) {
        List<Pair> playerPositions = actualPlayer == Player.X ? playerXPositions : playerOPositions;
        playerPositions.add(new Pair(row, column));
    }

//    private void handleAdjacentSigns(int[] adjacentSigns) {
//        int sumOfPairs = adjacentSigns[0] + adjacentSigns[3];
//        int sumOfSides = adjacentSigns[1] + adjacentSigns[2];
//        int maxDiagonal = adjacentSigns[4];
//
//        if (sumOfPairs == 2 || sumOfSides == 2 || maxDiagonal == 2) {
//            removeSignsRandomly(1);
//        } else if (sumOfPairs == 3 || sumOfSides == 3 || maxDiagonal == 3) {
//            removeSignsRandomly(2);
//        }
//    }

    private void switchPlayer() {
        actualPlayer = (actualPlayer == Player.X) ? Player.O : Player.X;
    }

//    public Player findWinner(int row, int column) {
//        int[] adjacentSigns = numOfAdjacentSigns(row, column);
//        
//        // Check for a win condition in any direction.
//        if (hasWinningCondition(adjacentSigns)) {
//            return table[row][column];
//        }
//
//        // If no win condition is met, return NOBODY.
//        return Player.NOBODY;
//    }
    
//    public Player findWinner(int row, int column) {
//    int[] adjacentSigns = numOfAdjacentSigns(row, column);
//
//    if (adjacentSigns[0] + adjacentSigns[3] + 1 >= 5 || // Vertical
//        adjacentSigns[1] + adjacentSigns[2] + 1 >= 5 || // Horizontal
//        adjacentSigns[4] + 1 >= 5) { // Diagonal
//        return actualPlayer; // return the player who made the last move
//    }
//
//    return Player.NOBODY;
//    }
    
    /**
     * Finds the winner of the game, if any, after a step at the specified row and column.
     * 
     * @param row The row index of the recent step.
     * @param column The column index of the recent step.
     * @return The player who has won the game or Player.NOBODY if there is no winner yet.
     */
    
    public Player findWinner(int row, int column) {
    int[] adjacentSigns = numOfAdjacentSigns(row, column);
    // Check for 5 in a row including the current mark
    if ((adjacentSigns[0] + adjacentSigns[3] == 4) || // Vertical
        (adjacentSigns[1] + adjacentSigns[2] == 4) || // Horizontal
        (adjacentSigns[4] == 4)) { // Diagonal
        // Return the actualPlayer since they would be the winner
        return actualPlayer;
    }
    // Otherwise, no winner yet
    return Player.NOBODY;
}
    
    private void handleAdjacentSigns(int[] adjacentSigns) {
        if(hasWinningCondition(adjacentSigns)) return;
        
        int sumOfPairs = adjacentSigns[0] + adjacentSigns[3];
        int sumOfSides = adjacentSigns[1] + adjacentSigns[2];
        int maxDiagonal = adjacentSigns[4];

        if (sumOfPairs == 2 || sumOfSides == 2 || maxDiagonal == 2) {
            removeSignsRandomly(1);
        } else if (sumOfPairs == 3 || sumOfSides == 3 || maxDiagonal == 3) {
            removeSignsRandomly(2);
        }
    }


//    private boolean hasWinningCondition(int[] adjacentSigns) {
//        return adjacentSigns[0] + adjacentSigns[3] == 4 || // Vertical
//               adjacentSigns[1] + adjacentSigns[2] == 4 || // Horizontal
//               adjacentSigns[4] == 4; // Diagonal
//    }
    
    
    private boolean hasWinningCondition(int[] adjacentSigns) {
    return adjacentSigns[0] + adjacentSigns[3] + 1 == 5 || // Vertical
           adjacentSigns[1] + adjacentSigns[2] + 1 == 5 || // Horizontal
           adjacentSigns[4] + 1 == 5; // Diagonal 
}

    /**
     * Gets the player who is currently taking their turn.
     * 
     * @return The current player.
     */
    
    public Player getActualPlayer() {
        return actualPlayer;
    }

}