package view;

import java.awt.Point;

import javax.swing.*;

import model.*;

public class BoardPanel extends JPanel implements Update{
	
	//instance variables
	private JLabel[][] board = new JLabel[20][20];
	
	private ImageIcon boardTileImage = new ImageIcon("images/gameBoardTile.png");
	private ImageIcon blueTileImage = new ImageIcon("images/blueTile.png");
	private ImageIcon redTileImage = new ImageIcon("images/redTile.png");
	private ImageIcon yellowTileImage = new ImageIcon("images/yellowTile.png");
	private ImageIcon greenTileImage = new ImageIcon("images/greenTile.png");
	
	private final int TILE_SIZE = 20;
	
	//This piece will be used to check if a piece placement is valid
	private Piece checkPiece = new Piece("name", "colour", "orientation", false);

	//constructor
	public BoardPanel() {
		super();
		setUpLabels();
		
		//setup check piece
		checkPiece.newBlock(0, 0); //add a single block to check piece
		
		setLayout(null);
	}
	
	private void setUpLabels() {
		//variables for setting position of board tiles
		int xPosition = 0;
		int yPosition = 0;
		
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board.length; y++) {
				board[x][y] = new JLabel(boardTileImage);
				board[x][y].setBounds(xPosition, yPosition, TILE_SIZE, TILE_SIZE);
				add(board[x][y]);
				
				//System.out.println(x + " "+ y + "|" + xPosition + " " + yPosition);
				
				yPosition += TILE_SIZE;
			}
			xPosition += TILE_SIZE;
			yPosition = 0;
		}
		
	}

	//update the visuals of the panel
	@Override
	public void update(Game game) {
		Board gameBoard = game.getBoard();
		
		//Update placed pieces
		//get the value of each tile on the board
		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 20; y++) {
				//Check the colour of each tile in the board and change the colour of label
				switch(gameBoard.getGameBoard()[x][y]) {
					case BLUE:
						board[x][y].setIcon(blueTileImage);
						break;
					case RED:
						board[x][y].setIcon(redTileImage);
						break;
					case YELLOW:
						board[x][y].setIcon(yellowTileImage);
						break;
					case GREEN:
						board[x][y].setIcon(greenTileImage);
						break;
					case null:
						board[x][y].setIcon(boardTileImage);
						
				}
				
				//Show valid moves
				//get the current player's turn colour
				Player currentPlayer = game.getPlayers().get(game.getCurrentPlayer());
				//PlayerColour colour = game.getPlayers().get(game.getCurrentPlayer()).getColor();
				//check if is valid move. piece used is single tile
				if (gameBoard.isValidMove(checkPiece, x, y, currentPlayer)) {
					//if it is a valid move, show it
					board[x][y].setIcon(blueTileImage);
				}
				
			}
			
		}
		
	}
	
	public void showGhostPiece(int x, int y) {
		//make sure the mouse is in the bounds of the panel
		if (x > 1 && x < 1 && y > 1 && y < 1 ) {
			
			
			
		}
		
	}
	
}
