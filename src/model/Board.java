package model;

public class Board {

	private int[][] gameBoard;
	private int size;

	public Board() {
		size = 20;
		gameBoard = new int[size][size];

	}

	private boolean isValidMove(Piece peice, int row, int col, Player player) {

		boolean touchesDiagonal = false;
		boolean firstMoveOnCorner = false;

		for (Block block : peice.getBlocks()) {

			int newRow = row + block.getRow();
			int newCol = col + block.getCol();

			// check if proposed position is on the board
			if (newRow < 0 || newRow > size - 1 || newCol < 0 || newCol > size - 1)
				return false;

			// check if the proposed position overlaps with a already taken square
			if (gameBoard[newRow][newCol] != 0) {
				return false;
			}

			// check for first move is on a corner

			int[][] corners = { { 0, 0 }, { 0, 19 }, { 19, 0 }, { 19, 19 } };

			if (player.isFirstMove())
				for (int[] corner : corners) {
					if (newRow == corner[0] && newCol == corner[1])
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
		if (player.isFirstMove())
			return firstMoveOnCorner;
		else
			return touchesDiagonal;

	}

	private boolean hasPossibleMove(Player player) {

		for (Piece peice : player.getPieces()) {
			if (searchMove(peice, 0, 0, player))
				return true;
		}

		return false;
	}

	private boolean searchMove(Piece peice, int row, int col, Player player) {
		
		boolean possibleMove = false;
		
		if (row == size)
			return false;

		for (int flip = 0; flip < 2; flip++) {

			for (int rotation = 0; rotation < 4; rotation++) {
				if (isValidMove(peice, row, col, player))
					possibleMove = true;
				
				peice.rotate();
			}
			
			peice.flip();
		}
		
		if (possibleMove)
			return true;

		if (col == size - 1)
			return (searchMove(peice, row + 1, 0, player));
		else
			return (searchMove(peice, row, col + 1, player));
	}
	
	private void place(Piece peice, int row, int col, Player player) {
		
		for (Block block: peice.getBlocks()) {
			
			int newRow = row + block.getRow();
			int newCol = col + block.getCol();
			
			gameBoard[newRow][newCol] = player.getColour();
			
		}
		
	}
}