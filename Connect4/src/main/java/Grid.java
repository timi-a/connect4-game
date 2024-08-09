/* 
  This class represents the game grid used for the Connect 4 Game. It 
  provides methods to initialize the board, print the board state, and manage 
  player actions. The class also includes methods to check for winning 
  conditions for both players.

  BUGS--------------------------------------------------------------------------
  - None at the moment 
  ------------------------------------------------------------------------------

 * Timi Aina
 * January 1, 2024
 */

//IMPORTS-----------------------------------------------------------------------
// None at the moment
//------------------------------------------------------------------------------
public class Grid {

    //Data Field----------------------------------------------------------------
    private final char[][] board;
    private final int[] lastChipPosition;
    //--------------------------------------------------------------------------

    //Constructor---------------------------------------------------------------
    /**
     * Constructs a Grid object with a default 7x6 board for the Connect 4 game.
     */
    public Grid() {
        this.board = new char[6][7];
        this.lastChipPosition = new int[2];
    }//Grid
    //--------------------------------------------------------------------------
    
    //Getters-------------------------------------------------------------------
    /**
     * Returns the coordinates of the last placed chip.
     * 
     * @return an array where the first element is the x-coordinate and the 
     * second element is the y-coordinate
     */
    public int[] getlastChipPosition() {
        return lastChipPosition;
    }
    //Methods-------------------------------------------------------------------
    /**
     * Prints the current state of the Connect 4 game board. 
     * The board includes a header and a visual representation of each cell.
     * Empty cells are represented by 'O'. 
     * Cells owned by player 1 are represented by "R" for "Red".
     * Cells owned by player 2 are represented by "Y" for "Yellow".
     */
    public void printBoard() {

        System.out.println("-- CONNECT 4 --"); 

        System.out.println("_______________"); 

        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {

            System.out.print("|"); //Start of the board

            for (int colIndex = 0; colIndex < board[rowIndex].length; colIndex++) {
                switch (board[rowIndex][colIndex]) {
                    case 'R' -> {
                        System.out.print(board[rowIndex][colIndex]);
                        System.out.print("|");
                    }
                    case 'Y' -> {
                        System.out.print(board[rowIndex][colIndex]);
                        System.out.print("|");
                    }
                    default -> {
                        board[rowIndex][colIndex] = 'O';
                        System.out.print(board[rowIndex][colIndex]);
                        System.out.print("|");
                    }
                }//switch
            }//for-loop

            System.out.println();

        }//for-loop

        System.out.println("_             _"); //Represents the board's legs

        System.out.println(); 

    }//printBoard

    /**
     * Clears the Connect 4 board, resetting all positions to 'O' (Empty).
     * This method is typically used to start a new game when a player enters
     * "YES" to play again.
     */
    public void clearBoard() {
        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {

            for (int colIndex = 0; colIndex < board[rowIndex].length; colIndex++) {

                board[rowIndex][colIndex] = 'O';

            }//for-loop

        }//for-loop   

    }//clearBoard

    /**
     * Adds a chip ('R' for player 1, 'Y' for player 2) at the specified column. 
     * The chip will "slide" down to the lowest empty space in the selected 
     * column.
     *
     * @param selectedPoint - the x-coordinate chosen by the player
     * @param playerChip - the character representing the player's chip ('R' or 'Y')
     * @return true if the chip was successfully placed, false if the column 
     * is full
     */
    public boolean playerPoint(XCoord selectedPoint, char playerChip) {

        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {

            if (board[(board.length - 1) - rowIndex][selectedPoint.getCol()] == 'O') {
                board[(board.length - 1) - rowIndex][selectedPoint.getCol()] = playerChip;
                
                lastChipPosition[0] = selectedPoint.getCol(); //column
                lastChipPosition[1] = (board.length - 1) - rowIndex; //row
                return true;
            }//if statement

        }//for-loop

        return false;
    }//player1Point

    /**
     * Checks if a player has won the game by connecting 4 chips horizontally.
     *
     * @param playerChip - the character representing the player's chip ('R' or 'Y')
     * @return true if a player has connected 4 horizontally, false otherwise
     */
    public boolean playerWinHori(char playerChip) {

        int consecutiveChips = 0;
        int rowIndex = lastChipPosition[1];
        
        //Checks the whole column to see if the player wins horizontally
        for (int colIndex = 0; colIndex < board[rowIndex].length; colIndex++) {

            if (board[rowIndex][colIndex] == playerChip) {
                consecutiveChips += 1;

                if (consecutiveChips == 4) {
                    return true;
                } // if statement
            } else {
                consecutiveChips = 0;
            } //else
        }//for-loop
        return false;
    }//playerWinHori

    /**
     * Checks if a player has won the game by connecting 4 chips vertically.
     *
     * @param playerChip - the character representing the player's chip ('R' or 'Y')
     * @return true if a player has connected 4 vertically, false otherwise
     */
    public boolean playerWinVert(char playerChip) {

        int consecutiveChips = 1; //The chip the player just put into the board
        int colIndex = lastChipPosition[0];
        
        //Checks the rows below to see if the player wins vertically
        for (int rowIndex = lastChipPosition[1] + 1; rowIndex < board.length; rowIndex++) {
            if (board[rowIndex][colIndex] == playerChip) {
                consecutiveChips += 1;
                if (consecutiveChips == 4) {
                    return true;
                }//if statement
            } else {
                consecutiveChips = 0;
            }//else               
        }//for-loop
        return false;
    }//playerWinVert

    /**
     * Checks if a player has won the game by connecting 4 chips diagonally from
     * left to right.
     *
     * @param playerChip - the character representing the player's chip ('R' or 'Y')
     * @return true if a player has connected 4 diagonally from left to right, 
     * false otherwise
     */
    public boolean playerWinDiag(char playerChip) {

        int consecutiveChips = 1; //The chip the player just put into the board
        int colIndex = lastChipPosition[0];
        int rowIndex = lastChipPosition[1];

        //Diagonal Up Right
        while (rowIndex > 0 && colIndex < board[rowIndex].length - 1) {
            rowIndex = rowIndex - 1;
            colIndex = colIndex + 1;
            if (board[rowIndex][colIndex] == playerChip) {
                consecutiveChips += 1;

                if (consecutiveChips == 4) {
                    return true;
                }//if statement
            } else {
                break;
            }//else 
        }//while
        
        //Resets the variables for the next diagonal test
        consecutiveChips = 1;
        colIndex = lastChipPosition[0];
        rowIndex = lastChipPosition[1];

        //Diagonal Up Left
        while (rowIndex > 0 && colIndex > 0) {
            rowIndex = rowIndex - 1;
            colIndex = colIndex - 1;
            if (board[rowIndex][colIndex] == playerChip) {
                consecutiveChips += 1;
      
                if (consecutiveChips == 4) {
                    return true;
                }//if statement
            } else {
                consecutiveChips = 0;
            }//else 
        }//while
        
        //Resets the variables for the next diagonal test
        consecutiveChips = 1;
        colIndex = lastChipPosition[0];
        rowIndex = lastChipPosition[1];

        //Diagonal Down Right
        while (rowIndex < board.length - 1 && colIndex < board[rowIndex].length - 1) {
            rowIndex = rowIndex + 1;
            colIndex = colIndex + 1;
            if (board[rowIndex][colIndex] == playerChip) {
                consecutiveChips += 1;
  
                if (consecutiveChips == 4) {
                    return true;
                }//if statement
            } else {
                consecutiveChips = 0;
            }//else 
        }//while

        //Resets the variables for the next diagonal test
        consecutiveChips = 1;
        colIndex = lastChipPosition[0];
        rowIndex = lastChipPosition[1];

        //Diagonal Down Left
        while (rowIndex < board.length - 1 && colIndex > 0) {
            rowIndex = rowIndex + 1;
            colIndex = colIndex - 1;
            if (board[rowIndex][colIndex] == playerChip) {
                consecutiveChips += 1;
   
                if (consecutiveChips == 4) {
                    return true;
                }//if statement
            } else {
                consecutiveChips = 0;
            }//else 
        }//while

        return false;
    }//playerWinDiag

    /**
     * Checks if a player has won the game by connecting 4 chips in any 
     * direction (horizontal, vertical, or diagonal).
     *
     * @param playerChip - the character representing the player's chip ('R' or 'Y')
     * @return true if a player has won, false otherwise
     */
    public boolean playerWin(char playerChip) {
        return (playerWinHori(playerChip) || playerWinVert(playerChip) || 
                playerWinDiag(playerChip));
    }//playerWin
}//Grid
