package model;

public class Board {

	private int[][] gameBoard;
	private int size;

	public Board() {
		size = 20;
		gameBoard = new int[size][size];

	}

	private boolean isValidMove(Piece peice, int row, int col, int playerColour) {

		boolean touchesDiagonal = false;
						
		for (Block block : peice.getBlocks()) {

			int newRow = row + block.getRow();
			int newCol = col + block.getCol();

			// check if proposed piece is on the board
			if (newRow < 0 || newRow > size - 1 || newCol < 0 || newCol > size - 1)
				return false;

			// check if the proposed position overlaps with a already taken square
			if (gameBoard[newRow][newCol] != 0) {
				return false;
			}

			// check if the desired placement is touching a side
			int[][] sides = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

			for (int[] side : sides) {
				int checkRow = newRow + side[0];
				int checkCol = newCol + side[1];

				if (checkRow >= 0 && checkRow < size && checkCol >= 0 && checkCol < size) {
				    if (gameBoard[checkRow][checkCol] == playerColour)
				        return false;
				}
			}

			// check if the desired placement is at a diagonal
			int[][] diagonals = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

			for (int[] diagonal : diagonals) {
				int checkRow = newRow + diagonal[0];
				int checkCol = newCol + diagonal[1];

				if (checkRow >= 0 && checkRow < size && checkCol >= 0 && checkCol < size) {
				    if (gameBoard[checkRow][checkCol] == playerColour)
				        touchesDiagonal = true;
				}
			}
			

		}

	    return touchesDiagonal;
	}

}
