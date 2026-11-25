package model;

import java.util.ArrayList;

public class Game {
	
	private Board board;
	private ArrayList<Player> players;
	private int currentPlayer;
	private boolean gameOver;
	
	public Game(ArrayList<Player> players) {
		super();
		this.board = new Board();
		this.players = players;
		this.currentPlayer = 0;
		this.gameOver = false;
	}

	public Board getBoard() {
		return board;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}

	public void nextPlayer() {
		if (!isGameOver()) {
			currentPlayer++;
			if (currentPlayer >= players.size())
				currentPlayer = 0;
		}
	}

	public boolean isGameOver() {
		return gameOver;
	}
	
	public void endGame() {
		gameOver = true;
	}
	
	public boolean hasPossibleMove(Player player) {
	    return board.hasPossibleMove(player);
	}
	
	public boolean hasAnyLegalMove() {

	    for (Player player : players) {
	        if (hasPossibleMove(player)) {
	            return true;
	        }
	    }

	    return false;
	}
	
	public boolean placePiece(Piece piece, int row, int col) {

	    Player player = getCurrentPlayer();

	    if (gameOver || piece == null || piece.isPlaced()) {
	        return false;
	    }

//	    if (!player.getPieces().contains(piece)) {
//	        return false;
//	    }

	    if (!board.place(piece, row, col, player)) {
	        return false;
	    }

	    piece.setPlaced(true);
	    player.addScore(piece.getBlocks().size());
	    player.setFirstMove(false);

	    return true;
	}
	
}
