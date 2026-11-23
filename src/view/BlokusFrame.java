package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

//This is the blokus frame
//It will be used to play blokus. It will feature the board, a panel to hold
//all the player's pieces, and a score board

public class BlokusFrame extends Frame{

	//instance variables
	private JLabel numRotAndFlipLabel = new JLabel();
	private JLabel playerTurnLabel = new JLabel();
	
	private Font labelFont = new Font("Arial", Font.BOLD, 20);
	
	//constructor
	public BlokusFrame(ActionListener listener) {
		super(listener);
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
		
		
		playerPanel.setBounds(750, 60, 400, 100);
		
		//add panels to frame
		
		
		add(playerPanel);
		
	}

	private void setUpLabels() {
		//create labels for number of rotations/flips and the player turn
		numRotAndFlipLabel.setText("Number of Rotation/Flip Left:");
		numRotAndFlipLabel.setForeground(Color.decode("#4287f5"));
		numRotAndFlipLabel.setFont(labelFont);
		numRotAndFlipLabel.setBounds(25, 25, 300, 50);
		add(numRotAndFlipLabel);
		
		playerTurnLabel.setText("Player 1's Turn!");
		playerTurnLabel.setForeground(Color.decode("#4287f5"));
		playerTurnLabel.setFont(labelFont);
		playerTurnLabel.setBounds(500, 25, 200, 50);
		add(playerTurnLabel);
		
	}

	private void updateBoard() {
		
	}
	
	private void updatePieces() {
		
	}
	
	private void updatePlayer() {
		
	}	
	
	private int showResults() {
		//sort players to find player with highest score
		
		//get the name of winning player 
		
		//Create option pane
		//source: https://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html
		int startNew = JOptionPane.showConfirmDialog(this, "Player Wins! \nStart a new game?", "Game Results", 
				JOptionPane.YES_NO_OPTION);
		
		//return the value of option selected for controller
		return startNew;
		
	}
	
}