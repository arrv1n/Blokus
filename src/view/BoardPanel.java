package view;

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

	@Override
	public void update(Game game) {
		Board board = game.getBoard();
		
		
	}
	

}
