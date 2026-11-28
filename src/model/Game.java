/*
Name: Sean and Arvin
Title: Blockus - Game Class

Description:
This is the game class it runs the blockus games and is the what stores all the information about the game.
It tracks the board, the players, whos turn it is and when the games over.
*/

package model;

import java.util.ArrayList;

public class Game {

	//fields
	private Board board;
	private ArrayList<Player> players;
	private int currentPlayer;
	private boolean gameOver;

	//constructors
	public Game(ArrayList<Player> players) {
		super();
		this.board = new Board();
		this.players = players;
		this.currentPlayer = 0;
		this.gameOver = false;
	}

	//getter for board
	public Board getBoard() {
		return board;
	}

	//getter for players
	public ArrayList<Player> getPlayers() {
		return players;
	}

	//getter for current player which stores whos turn it is
	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}

	//method that sets the turn to the next player
	public void nextPlayer() {
		if (!isGameOver()) {
			currentPlayer++;
			if (currentPlayer >= players.size())
				currentPlayer = 0;
		}
	}

	//method that checks if the game is over
	public boolean isGameOver() {
		return gameOver;
	}

	//method that ends the game
	public void endGame() {
		gameOver = true;
	}

	//method that checks if theres a possible move for a given player
	public boolean hasPossibleMove(Player player) {
	    return board.hasPossibleMove(player);
	}

	//method that checks if there are any possible moves for all the players
	public boolean hasAnyLegalMove() {

	    for (Player player : players) {
	        if (hasPossibleMove(player)) {
	            return true;
	        }
	    }

	    return false;
	}

	//method that places a peice
	public boolean placePiece(Piece piece, int row, int col) {

	    Player player = getCurrentPlayer();

	    if (gameOver || piece == null || piece.isPlaced()) {
	        return false;
	    }

	    if (!board.place(piece, row, col, player)) {
	        return false;
	    }

	    piece.setPlaced(true);
	    player.addScore(piece.getBlocks().size());
	    player.setFirstMove(false);

	    return true;
	}
	
}
