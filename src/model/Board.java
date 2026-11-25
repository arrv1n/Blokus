package model;

public class Board {

	private PlayerColour[][] gameBoard;
	public static final int size = 20;

	public Board() {
		gameBoard = new PlayerColour[size][size];

	}
	
	public PlayerColour getGameBoard(int row, int col) {
	    return gameBoard[row][col];
	}

	public boolean isValidMove(Piece peice, int row, int col, Player player) {

		boolean touchesDiagonal = false;
		boolean firstMoveOnCorner = false;

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
//				for (int[] corner : corners) {
//					if (newRow == corner[0] && newCol == corner[1])
//						firstMoveOnCorner = true;
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
		if (player.isFirstMove())
			return firstMoveOnCorner;
		else
			return touchesDiagonal;

	}

	public boolean hasPossibleMove(Player player) {

		for (Piece piece : player.getPieces()) {
			if (!piece.isPlaced()) {
				if (searchMove(piece, 0, 0, player))
					return true;
			}
		}

		return false;
	}

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
	
	public boolean place(Piece piece, int row, int col, Player player) {
		
		if (!isValidMove(piece, row, col, player)) {
	        return false;
	    }
		
		for (Block block: piece.getBlocks()) {
			
			int newRow = row + block.getRow();
			int newCol = col + block.getCol();
			
			gameBoard[newRow][newCol] = player.getColour();
			
		}
		return true;
	}
}
