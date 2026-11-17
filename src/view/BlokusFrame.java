package View;

import javax.swing.*;

//This is the blokus frame
//It will be used to play blokus. It will feature the board, a panel to hold
//all the player's pieces, and a score board

public class BlokusFrame extends Frame{

	//instance variables
	private JLabel numRotateAndFlipLabel = new JLabel();
	private JLabel playerTurnLabel = new JLabel();
	
	
	//constructor
	public BlokusFrame() {
		super();
		setUpBlokusFrame();
		
	}
	
	private void setUpBlokusFrame() {
		setUpPanels();
		setUpLabels();
		
		setTitle("Blokus");
		setVisible(true);
		
	}

	private void setUpPanels() {
		//create blokus, piece, and score panel
		BoardPanel boardPanel = new BoardPanel();
		PiecePanel piecePanel = new PiecePanel();
		PlayerPanel playerPanel = new PlayerPanel();
		
		//set bounds for each panel
		
	}

	private void setUpLabels() {
		//create labels for number of rotations/flips and the player turn
		
	}

	private void updateBoard() {
		
	}
	
	private void updatePieces() {
		
	}
	
	private void updatePlayer() {
		
	}	
	
	private void showResults() {
		
	}
	
}
