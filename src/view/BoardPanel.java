package view;

import java.awt.Point;

import javax.swing.*;

import model.*;

public class BoardPanel extends JPanel implements Update{
	
	//instance variables
	private JLabel[][] board = new JLabel[20][20];
	
	private ImageIcon boardTileImage = new ImageIcon("images/gameBoardTile.png");
	
	private final int TILE_SIZE = 20; 

	//constructor
	public BoardPanel() {
		super();
		setUpLabels();
		
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
		Board board = game.getBoard();
		
		//Update placed pieces
		//get the value of each tile on the board
		for (int i = 0; i < 20; i++) {
			for (int e = 0; e < 20; e++) {
				//System.out.println(board[i][e]);
				
				
			}
		}
		
		//Show valid moves
		//get the current player's turn colour
		//repeat through all tiles
		//check if is valid move. piece used is single tile
		//if it is a valid move, show it
		
	}
	
	public void showGhostPiece(int x, int y) {
		//make sure the mouse is in the bounds of the panel
		if (x > 1 && x < 1 && y > 1 && y < 1 ) {
			
			
			
		}
		
	}
	
}
