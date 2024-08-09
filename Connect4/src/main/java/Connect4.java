/* 
  This class is used to. 

  BUGS--------------------------------------------------------------------------
  - None at the moment
  ------------------------------------------------------------------------------

 * Timi Aina
 * January 1, 2024
 */

//IMPORTS-----------------------------------------------------------------------
import java.util.Scanner;
import java.util.Stack;
import java.util.Random;
import java.util.InputMismatchException;
//------------------------------------------------------------------------------

public class Connect4 {

    /**
     * 
     * 
     * @param args - the command line arguments (none) 
     */
    public static void main(String[] args) {
        
        gameHeader();

        Scanner input = new Scanner(System.in);

        Stack<String> listOfPlayers = new Stack<>();

        addPlayers(input, listOfPlayers);

        Grid connect4Board = new Grid();

        Random rand = new Random();

        System.out.println();

        roundSystem(connect4Board, rand, listOfPlayers, input);

    }//main

    /**
     * This method is used to create the game header for the Connect 4 game.
     */
    public static void gameHeader() {
        System.out.println("_________________________________________________");
        System.out.println();
        System.out.println("-/-/-/-/-/-/-/-/-/- CONNECT 4 -/-/-/-/-/-/-/-/-/-");
        System.out.println("_________________________________________________");
    }//gameHeader

    /**
     * This method is used to add the two players that will play the game.
     * @param input - a Scanner object
     * @param listOfPlayers - a Stack object
     */
    public static void addPlayers(Scanner input, Stack<String> listOfPlayers) {

        System.out.println();

        for (int playerNum = 0; playerNum <= 1; playerNum++) {
            System.out.print("Enter Name Of Player: ");
            listOfPlayers.push(input.nextLine());
        }
    }//addPlayers

    /**
     * This method is used to choose which player starts the game first.
     * @param listOfPlayers - a Stack object
     * @param rand - a Random object
     */
    public static void chooseOrder(Stack<String> listOfPlayers, Random rand) {

        boolean playerStarting = rand.nextBoolean();

        if (playerStarting) {
            String player1 = listOfPlayers.pop();
            String player2 = listOfPlayers.pop();
            System.out.println(player1 + " is Player 1! ");
            System.out.println(player2 + " is Player 2! ");

            System.out.println();
        } else {
            String player2 = listOfPlayers.pop();
            String player1 = listOfPlayers.pop();

            System.out.println(player1 + " is Player 1! ");
            System.out.println(player2 + " is Player 2! ");

            System.out.println();
        }//else

    }//chooseOrder

    /**
     * This method allows input from both players, alternates turns between 
     * them, and records each player's input until a player wins the game or the 
     * board is filled.
     * 
     * @param connect4Board - a Grid object
     * @param rand - a Random object
     * @param listOfPlayers - a Stack object
     * @param input - a Scanner object
     */
    public static void roundSystem(Grid connect4Board, Random rand,
            Stack<String> listOfPlayers, Scanner input) {

        boolean wonGame = false;
       
        int numOfChips = 0;
        
        int minChipsToWin = 7;
        
        boolean isTie = false;

        boolean player1Turn = true; //Value decides for both player's turns 
        
        char player1Chip = 'R';
        
        char player2Chip = 'Y';
        
        chooseOrder(listOfPlayers, rand);

        while (!wonGame) {

            try{
                
                if (numOfChips == 42){
                    isTie = true;
                    break;
                }//if statement
                
                connect4Board.printBoard();
                
                if (player1Turn) {

                    System.out.print("Player 1, pick a row number (1-7) to place your chip: ");                    
                    
                    int x = input.nextInt();
                    XCoord userInputtedPoint = new XCoord(x);

                    if (x < 1 || x > 7) {
                        System.out.println("Please choose a valid row number (1-7):");
                    } else if (!connect4Board.playerPoint(userInputtedPoint, player1Chip)) {
                        System.out.println("Please place your chip in a column that is not full. ");
                    } else {

                        System.out.println();
                        numOfChips += 1;
                        
                        if (numOfChips >= minChipsToWin) {

                            wonGame = connect4Board.playerWin(player1Chip);

                            if (wonGame) {
                                break;
                            }//if statement
                        }//if statement
                        
                        player1Turn = false;
                    }//else

                } else {

                    System.out.print("Player 2, pick a row number (1-7) to place your chip: ");                   
                    
                    int x = input.nextInt();
                    XCoord userInputtedPoint = new XCoord(x);

                    if (x < 1 || x > 7){
                        System.out.print("Please choose a valid row number (1-7):");
                    } else if (!connect4Board.playerPoint(userInputtedPoint, player2Chip)) {
                        System.out.println("Please place your chip in a column that is not full. ");
                    } else {

                        System.out.println();
                        numOfChips += 1;

                        if (numOfChips >= minChipsToWin) {

                            wonGame = connect4Board.playerWin(player2Chip);

                            if (wonGame) {
                                break;
                            }//if statement
                        }//if statement

                        player1Turn = true;
                    }//else

                }//else
            } catch (InputMismatchException e){
                    System.out.println("Please enter a valid row number (1-7)");
                    input.nextLine(); //Clears the inputs made
                }//catch
                
        }//while loop
        
        endGame(connect4Board, input, listOfPlayers, rand, player1Turn, isTie);        

    }//roundSystem

    /**
     * This method prints out the player that won and calls the
     * "playAnotherGame" method.
     * @param connect4Board - a Grid object
     * @param input - a Scanner object
     * @param listOfPlayers - a Stack object
     * @param rand - a Random object
     * @param player1Turn
     * @param isTie
     */
    public static void endGame(Grid connect4Board, Scanner input, 
            Stack<String> listOfPlayers, Random rand, boolean player1Turn, 
            boolean isTie){
        
        if (isTie){
                        connect4Board.printBoard();
            System.out.println("-----------------------------------------------"
                    + "--");
            System.out.println("              CONNECT 4 NOT ACHIEVED           "
                    + "  ");
            System.out.println("-----------------------------------------------"
                    + "--");   
            playAnotherGame(connect4Board, input, listOfPlayers, rand, false);
        } else if (player1Turn){
                        connect4Board.printBoard();
            System.out.println("-----------------------------------------------"
                    + "--");
            System.out.println("            CONNECT 4: PLAYER 1 WINS!          "
                    + "  ");
            System.out.println("-----------------------------------------------"
                    + "--");
            System.out.println(); //Creates a blank line
            playAnotherGame(connect4Board, input, listOfPlayers, rand, false);
        } else {
            connect4Board.printBoard();
            System.out.println("-----------------------------------------------"
                    + "--");
            System.out.println("            CONNECT 4: PLAYER 2 WINS!          "
                    + "  ");
            System.out.println("-----------------------------------------------"
                    + "--");
            System.out.println(); //Creates a blank line
            playAnotherGame(connect4Board, input, listOfPlayers, rand, false);
        }
        
    }//endGame
    
    /**
     * This method asks the user if they want to play Connect 4 again.
     * @param connect4Board - a Grid object
     * @param input - a Scanner object
     * @param listOfPlayers - a Stack object
     * @param rand - a Random object
     * @param wrongInput - checks if the player has made a wrong input resulting
     * in a message being displayed more than once.
     */
    public static void playAnotherGame(Grid connect4Board, Scanner input, 
            Stack<String> listOfPlayers, Random rand, boolean wrongInput){
        
        String playAgain; 
            
        if (!wrongInput) {
            input.nextLine(); //Clears enter key
            System.out.print("Do you want to play again (YES/NO): ");
            playAgain = input.nextLine();
        } else {
            System.out.print("Do you want to play again (YES/NO): ");
            playAgain = input.nextLine();
        }//else
            
            switch (playAgain){
                case "YES","Yes","yes" -> {
                    gameHeader();
                    addPlayers(input, listOfPlayers);
                    connect4Board.clearBoard();
                    roundSystem(connect4Board, rand, listOfPlayers, input);
                }
                case "NO", "No", "no" -> {
                    System.out.println();
                    System.out.println("---------------------------------------"
                            + "----------");
                    System.out.println("       Thanks for playing Connect 4!");
                    System.out.println("---------------------------------------"
                            + "----------");                    
                }
                default ->{
                    System.out.println("ERROR: Enter \"YES\" or \"NO\"");
                    playAnotherGame(connect4Board, input, listOfPlayers, rand,
                            true);
                }
            }//switch        
    }//playAgain
}//Connect4
