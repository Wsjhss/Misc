package unit5;
import java.io.*;

//By Jasmine Wang August 2023

/**
 * TicTacToe is a program that simulates a game of Tic-Tac-Toe, 
 * it requires two players or one who play against themselves.
 */

public class TicTacToe {

	//variables
	static char[][] board = new char[3][3]; // playable board area, a 3x3 grid
    static boolean gameOver = false;        // gameOver is set to true if a player has won the game, or it's a draw
    static boolean exit = false;            // exit is set to true when the player chooses to exit after a game is over
    static char player1 = 'O';              // player 1's sign is O
    static char player2 = 'X';              // player 2's sign is X
    static char turn = player1;             // turn specifies which player's turn it is
	
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException 
	{
		//put "_" as characters in the array
		clearBoard();
		
		System.out.println("Welcome to Tic-Tac-Toe!");
		
		while(!exit)
		{
			System.out.println("Player " + turn + "\'s turn.");
			printBoard();
			move(turn);
		}//end while
	}//end main
	
	
	/**
	 * clearBoard clears the board (put "_" as characters in the array) and set turn to player1
	 * @param none
	 * @return none
	 */
	public static void clearBoard ()
	{
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
			{
				board[i][j]= '_';
			}//end inner for
		}//end outer for
		turn = player1;
		System.out.println("Game start!");
	}//end clearBoard


	/**
	 * printBoard prints out the 3x3 playing area
	 * @param none
	 * @return none
	 * 
	 * Set up:
	 *    0   1   2 \n
	 * 0  _ | _ | _ \n
	 * 1  _ | _ | _ \n
	 * 2  _ | _ | _ \n
	 *
	 */
	public static void printBoard ()
	{
		System.out.println("   0   1   2 \n"
				+ "0  "+ board[0][0] +" | "+board[0][1]+" | "+board[0][2]+" \n"
				+ "1  "+board[1][0]+" | "+board[1][1]+" | "+board[1][2]+" \n"
				+ "2  "+board[2][0]+" | "+board[2][1]+" | "+board[2][2]+" \n");
	}//end printBoard
	
	/**
	 * move lets the player place their O or X on the board
	 * @param char p, the player that is making the move
	 * @return none
	 * @throws IOException 
	 */
	public static void move (char p) throws IOException
	{
		String input; //to get input from user
		int row = 3; 
		int column = 3; //the row and column the player choose to place their mark in
		boolean playable = false; //set to true when the grid the player choose is free
		boolean rowCount; //true if row number is obtained
		boolean columnCount; //true if column number is obtained
		
		while(!playable)
		{
			rowCount= false;
			columnCount = false;
			
			//get row number
			while(!rowCount)
			{
				System.out.println("Choose the row to place your mark in, 0, 1 or 2");
				input = in.readLine();
				if(input.equals("0")||input.equals("1")|| input.equals("2") )
				{
					row = Character.getNumericValue(input.charAt(0));
					rowCount= true;
				}//end if
				else
				{
					System.out.println("That wasnt a valid option. Please enter 0, 1 or 2.");
				}//end else
			}//end inner while
			
			//get column number
			while(!columnCount)
			{
				System.out.println("Choose the column to place your mark in, 0, 1 or 2");
				input = in.readLine();
				if(input.equals("0")||input.equals("1")|| input.equals("2") )
				{
					column = Character.getNumericValue(input.charAt(0));
					columnCount= true;
				}//end if
				else
				{
					System.out.println("That wasnt a valid option. Please enter 0, 1 or 2.");
				}//end else
			}//end inner while
			
			//check if the grid the player chose is free
			if (board[row][column]== '_')
			{
				board[row][column]= p;
				printBoard();
				System.out.println("Placed!");
				playable= true;
			}//end if
			else
			{
				playable = false;
				System.out.println("Sorry, that grid is taken. Try again!");
			}//end else
			
		}//end while
		
		//change turns
		if (turn == player1)
		{turn = player2;}
		else
		{turn = player1;}
		
		if (checkWin(p))
		{
			System.out.println("Player "+ p + " wins!");
			gameOver= true;
		}
		else if(checkDraw(p))
		{
			System.out.println("It's a draw!");
			gameOver= true;
		}
		else {gameOver=false;}
		
		if(gameOver)
		{
			while (true)
			{
				System.out.println("Do you want to start a new game? y or n");
				input = in.readLine();
				if(input.compareToIgnoreCase("y")== 0)
				{
					clearBoard();
					System.out.println("A new game has started.");
					gameOver= false;
					break;
				}
				else if (input.compareToIgnoreCase("n")== 0)
				{
					System.out.println("Game ended. Goodbye!");
					exit = true;
					break;
				}
				else
				{System.out.println("Invalid input, try again.");}
			}//end while
		}//end if
		
	}//end move

	/**
	 * checkWin check if the player playing have won the game by checking every scenario
	 * @param char p, the player that is making the move
	 * @return boolean, win or no
	 */
	public static boolean checkWin (char p)
	{
		boolean win= false;
		
		
		for(int i =0; i<3;i++)
		{
			//horizontal win
			if (board[i][0] == p && board[i][1] == p && board[i][2] == p)
			{
                win = true;
			}
			
			//vertical win
	        if (board[0][i] == p && board[1][i] == p && board[2][i] == p)
	        {
	            win = true;
	        }
	        
	        //diagonal wins
	        if (board[0][0] == p && board[1][1] == p && board[2][2] == p)
	        {
	            win = true;
	        }
	        if (board[0][2] == p && board[1][1] == p && board[2][0] == p)
	        {
	            win = true;
	        }
		}//end for
		
		return win;
	}//end checkWin
	
	
	/**
	 * checkDraw checks if there is a draw (if the board is full and there isn't a win)
	 * @param char p, the player that is making the move
	 * @return boolean, draw or no
	 */
	public static boolean checkDraw (char p)
	{
		boolean draw = true;
		for (int i = 0; i < 3; i ++) 
		{
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] != player1 && board[i][j] != player2)
                {
                    draw = false;
                }//end if
            }//end inner for
		}//end outer for
		return draw;
	}//end checkDraw
	
}//end class
