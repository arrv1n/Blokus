package model;

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

	public void nextPlayer() {
		if (isGameOver()) {
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
	
}
