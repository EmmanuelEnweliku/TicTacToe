package tictactoe.tictactoe;

import java.util.*;

//Brief Explanation.
/*
  I.   Create and print gameBoard.
  II.  Create gameLoop(Loops after each turn from the user).
  III. Get input from user using the scanner method on a position of (1-9) in the gameBoard, 
       store it and check for winner.
  IV.  CPU inputs and the input gets stored the check for winner.
  V.   As you check for winner, store win conditions and check if any of the stored positions have the condition.
  VI.  Then you repeat the III and IV process until either The User/CPU wins or there's a tie.

 This done using the printGameBoard, placePiece and checkWinner Method.
*/
public class TicTacToe 
{
    //Used to store position of player
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    //Used to store position of CPU
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String args[])
    {
        char [][] gameBoard = {{' ', '|', ' ', '|',' '}, 
                           {'-', '+', '-', '+','-'}, 
                           {' ', '|', ' ', '|',' '}, 
                           {'-', '+', '-', '+','-'}, 
                           {' ', '|', ' ', '|',' '}};
        
        //Prints initial gameboard
        printGameBoard(gameBoard);
        
        while(true)
        {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter your placement (1-9):");
            int playerPos = scan.nextInt();
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos))
            {
                System.out.print("Positin taken! Enter correct position: ");
                playerPos = scan.nextInt();
            }
            
            //Player method
            placePiece(gameBoard, playerPos, "player");
            
            String result = checkWinner();
            if(result.length() > 0)
            {
                System.out.println(result);
                break;
            }
            
        
            Random rand =  new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos))
            {
                cpuPos = rand.nextInt(9) + 1;
            }
            
            //CPU method
            placePiece(gameBoard, cpuPos, "cpu");
            
            //Prints gameboard with pieces on it.
            printGameBoard(gameBoard);
            
            result = checkWinner();
            if(result.length() > 0)
            {
                System.out.println(result);
                break;
            }
        }
    }
    
    public static void printGameBoard(char [][] gameBoard)
    {
        for(char [] row : gameBoard)
        {
            for(char c : row)
            {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    
    public static void placePiece(char [][] gameBoard, int pos, String user)
    {
        
        char symbol = ' ';
        if(user.equals("player"))
        {
            symbol = 'X';
            playerPositions.add(pos);
        }
        else if(user.equals("cpu"))
        {
            symbol = 'O';
            cpuPositions.add(pos);
        }
        
        
        //Switch statement used to switch between player and cpu.
        switch(pos)
        {
            case 1:
                gameBoard[0][0] = symbol;
                break;
                
            case 2:
                gameBoard[0][2] = symbol;
                break;
                
            case 3:
                gameBoard[0][4] = symbol;
                break;
                
            case 4:
                gameBoard[2][0] = symbol;
                break;
                
            case 5:
                gameBoard[2][2] = symbol;
                break;
                
            case 6:
                gameBoard[2][4] = symbol;
                break;
                
            case 7:
                gameBoard[4][0] = symbol;
                break;
                
            case 8:
                gameBoard[4][2] = symbol;
                break;
                
            case 9:
                gameBoard[4][4] = symbol  ;
                break;
            default:
                break;
        }
    }
    
    public static String checkWinner()
    {
        /*
          mid = middle
          bot = bottom
          col = column
        */
        
        //Rows
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        
        //Columns
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        
        //Diagonals crosses
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);
        
        List<List> winning = new ArrayList<List>();  // winning = winning conditions.
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);    
        
        for(List l : winning)
        {
            if(playerPositions.containsAll(l))
            {
                return "Congratulations you won!";
            }
            else if(cpuPositions.contains(l))
            {
                return "CPU wins! Sorry :(";
            }
            else if(playerPositions.size() + cpuPositions.size() == 9)
            {
                return "CAT!";
            }
        }
        return "";
    }
}
