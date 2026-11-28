/*
Name: Sean Lee
Title: Blockus - Board Class

Description:
The board class represents the 20x20 game board for the blockus game. Each square 
stores the player colour of the peice covering that square or null of it is empty.
Board class also contains all the methods related to placement rules of the peices.
*/

package model;

public class Board {

	//fields
	private PlayerColour[][] gameBoard;
	public static final int size = 20;

	//constructor to create the game board
	public Board() {
		gameBoard = new PlayerColour[size][size];

	}

	//method to get the game board array
	public PlayerColour getGameBoard(int row, int col) {
	    return gameBoard[row][col];
	}

	//method to assign a square to a colour
	public void setSquare(int row, int col, PlayerColour colour) {
    	gameBoard[row][col] = colour;
	}	

	//method to check if a move is valid
	public boolean isValidMove(Piece peice, int row, int col, Player player) {

		boolean touchesDiagonal = false;
		boolean firstMoveOnCorner = false;

		//this for loop checks if each block in a peice is within the board
		for (Block block : peice.getBlocks()) {

			int newRow = row + block.getRow();
			int newCol = col + block.getCol();

			// check if proposed position is on the board
			if (newRow < 0 || newRow > size - 1 || newCol < 0 || newCol > size - 1)
				return false;

			// check if the proposed position overlaps with a already taken square
			if (gameBoard[newRow][newCol] != null) {
				return false;
			}

			// check for first move is on a corner
			int[][] corners = { { 0, 0 }, { 0, 19 }, { 19, 0 }, { 19, 19 } };

			if (player.isFirstMove()) {
				if (newRow == player.getColour().getStartRow() && newCol == player.getColour().getStartCol())
					firstMoveOnCorner = true;
			}

			// check if the desired placement is touching a side
			int[][] sides = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

			for (int[] side : sides) {
				int checkRow = newRow + side[0];
				int checkCol = newCol + side[1];

				if (checkRow >= 0 && checkRow < size && checkCol >= 0 && checkCol < size) {
					if (gameBoard[checkRow][checkCol] == player.getColour())
						return false;
				}
			}

			// check if the desired placement is at a diagonal
			int[][] diagonals = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

			for (int[] diagonal : diagonals) {
				int checkRow = newRow + diagonal[0];
				int checkCol = newCol + diagonal[1];

				if (checkRow >= 0 && checkRow < size && checkCol >= 0 && checkCol < size) {
					if (gameBoard[checkRow][checkCol] == player.getColour())
						touchesDiagonal = true;
				}
			}

		}

		//this returns whether or not the move is valid. If the move did not 
		//pass any of the rules before it will return false and this part will not be 
		//used
		if (player.isFirstMove())
			return firstMoveOnCorner;
		else
			return touchesDiagonal;

	}

	//this method checks if a player has any possible moves left
	public boolean hasPossibleMove(Player player) {

		for (Piece piece : player.getPieces()) {
			if (!piece.isPlaced()) {
				if (searchMove(piece, 0, 0, player))
					return true;
			}
		}

		return false;
	}

	//this method searches if there is a possible move using recurrsion
	public boolean searchMove(Piece piece, int row, int col, Player player) {
		
		boolean possibleMove = false;
		
		// base case
		if (row == size)
			return false;

		for (int flip = 0; flip < 2; flip++) {
			
			for (int rotation = 0; rotation < 4; rotation++) {
				
				if (isValidMove(piece, row, col, player))
					possibleMove = true;
				
				piece.rotate();
			}
			
			piece.flip();
		}
		
		if (possibleMove)
			return true;

		if (col == size - 1)
			return (searchMove(piece, row + 1, 0, player));
		else
			return (searchMove(piece, row, col + 1, player));
	}

	//this method places a peice on the board
	public boolean place(Piece piece, int row, int col, Player player) {

		//first makes sure the move is valid
		if (!isValidMove(piece, row, col, player)) {
	        return false;
	    }

		//places each block in its correct spot
		for (Block block: piece.getBlocks()) {
			
			int newRow = row + block.getRow();
			int newCol = col + block.getCol();
			
			gameBoard[newRow][newCol] = player.getColour();
			
		}
		return true;
	}
}
