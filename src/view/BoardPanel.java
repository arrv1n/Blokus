package view;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.*;

import model.*;

public class BoardPanel extends JPanel implements Update, MouseListener{
	
	//instance variables
	private JLabel[][] board = new JLabel[20][20];
	
	private ImageIcon[] tileImages = {new ImageIcon("images/gameBoardTile.png"),
									  new ImageIcon("images/blueTile.png"),
									  new ImageIcon("images/redTile.png"),
									  new ImageIcon("images/yellowTile.png"),
									  new ImageIcon("images/greenTile.png")};
	
	private ImageIcon[] validTileImages = {new ImageIcon("images/blueValidTile.png"),
										   new ImageIcon("images/redValidTile.png"),
										   new ImageIcon("images/yellowValidTile.png"),
										   new ImageIcon("images/greenValidTile.png")};
	
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
		
		//create labels and add to board
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board.length; y++) {
				//create the labels and set bounds
				board[x][y] = new JLabel(tileImages[0]);
				board[x][y].setBounds(xPosition, yPosition, TILE_SIZE, TILE_SIZE);
				board[x][y].addMouseListener(this);
				add(board[x][y]);
				
				yPosition += TILE_SIZE; //Increment y position (go down a row)
			}
			xPosition += TILE_SIZE; //increment x position (go to next column)
			yPosition = 0; //rest y position (go back to top row)
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
						board[x][y].setIcon(tileImages[1]);
						break;
					case RED:
						board[x][y].setIcon(tileImages[2]);
						break;
					case YELLOW:
						board[x][y].setIcon(tileImages[3]);
						break;
					case GREEN:
						board[x][y].setIcon(tileImages[4]);
						break;
					case null:
						board[x][y].setIcon(tileImages[0]);
						
				}
				
				//Show valid moves
				//get the current player's turn colour
				Player currentPlayer = game.getPlayers().get(game.getCurrentPlayer());
				//PlayerColour colour = game.getPlayers().get(game.getCurrentPlayer()).getColor();
				//check if is valid move. piece used is single tile
				if (gameBoard.isValidMove(checkPiece, x, y, currentPlayer)) {
					//if it is a valid move for the current player's colour, show it
					switch(currentPlayer.getColor()) {
					case BLUE:
						board[x][y].setIcon(validTileImages[0]);
						break;
					case RED:
						board[x][y].setIcon(validTileImages[1]);
						break;
					case YELLOW:
						board[x][y].setIcon(validTileImages[2]);
						break;
					case GREEN:
						board[x][y].setIcon(validTileImages[3]);
						break;
					
					}
				
				}
			}
		}
		
	}
	
	public void showGhostPiece(Piece piece, int x, int y) {
		//get the row and column values of the hovered
		//Since blocks are 20x20 pixels, dividing the coordinates of the label
		//by 20 will get the row and column
		int row = x / 20;
		int col = y / 20;
		
		for (Block block: piece.getBlocks()) {
			//Get where the blocks will go
			int newRow = row + block.getRow();
			int newCol = col + block.getCol();
		
			//change the tiles on the board
			board[newRow][newCol].setIcon(tileImages[1]);
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//TESTING
	@Override
	public void mouseEntered(MouseEvent e) {
		//get the label that is being hovered over
		//.getSource returns an object, so it has to be downcasted
		//to a JLabel so that we can get the coordinates of the JLabel
		JLabel tile = (JLabel) e.getSource();
		
		showGhostPiece(checkPiece, tile.getX(), tile.getY());
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//for controller
		//update the board to remove the ghost pieces
		//update(game);
		
	}
	
}