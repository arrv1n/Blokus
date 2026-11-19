package model;

public class Game {
	
	private Board board;
	private ArrayList<Player> players;
	private int currentPlayer;
	private boolean gameOver;
	
	public Game(Board board, ArrayList<Player> players, int currentPlayer, boolean gameOver) {
		super();
		board = new Board();
		this.players = players;
		currentPlayer = 0;
		gameOver = false;
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
