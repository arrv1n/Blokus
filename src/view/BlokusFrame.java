package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import model.Game;

//This is the blokus frame
//It will be used to play blokus. It will feature the board, a panel to hold
//all the player's pieces, and a score board

public class BlokusFrame extends Frame {

	//instance variables
	private JLabel numRotAndFlipLabel = new JLabel();
	private JLabel playerTurnLabel = new JLabel();
	
	private BoardPanel boardPanel;
	private PiecePanel piecePanel;
	private PlayerPanel playerPanel;
	
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
		boardPanel = new BoardPanel();
		piecePanel = new PiecePanel();
		playerPanel = new PlayerPanel();
		//create scroll pane for piece panel
		JScrollPane scrollPanel = new JScrollPane(piecePanel);
		
		//set bounds for each panel
		boardPanel.setBounds(150, 100, 400, 400);
		scrollPanel.setBounds(750, 175, 400, 350);
		playerPanel.setBounds(750, 60, 400, 100);
		
		//add panels to frame
		add(boardPanel);
		add(scrollPanel);
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

	public void updateBoard(Game game) {
		boardPanel.update(game);
	}
	
	public void updatePieces(Game game) {
		piecePanel.update(game);
	}
	
	public void updatePlayer(Game game) {
		playerPanel.update(game);
	}	
	
	public int showResults() {
		//sort players to find player with highest score
		
		//get the name of winning player 
		
		//Create option pane
		//source: https://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html
		int startNew = JOptionPane.showConfirmDialog(this, "Player Wins! \nStart a new game?", "Game Results", 
				JOptionPane.YES_NO_OPTION);
		
		//return the value of option selected for controller
		return startNew;
		
	}

	//Getters and setters
	public JLabel getNumRotAndFlipLabel() {
		return numRotAndFlipLabel;
	}

	public void setNumRotAndFlipLabel(JLabel numRotAndFlipLabel) {
		this.numRotAndFlipLabel = numRotAndFlipLabel;
	}

	public JLabel getPlayerTurnLabel() {
		return playerTurnLabel;
	}

	public void setPlayerTurnLabel(JLabel playerTurnLabel) {
		this.playerTurnLabel = playerTurnLabel;
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	public void setBoardPanel(BoardPanel boardPanel) {
		this.boardPanel = boardPanel;
	}

	public PiecePanel getPiecePanel() {
		return piecePanel;
	}

	public void setPiecePanel(PiecePanel piecePanel) {
		this.piecePanel = piecePanel;
	}

	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}

	public void setPlayerPanel(PlayerPanel playerPanel) {
		this.playerPanel = playerPanel;
	}

	public Font getLabelFont() {
		return labelFont;
	}

	public void setLabelFont(Font labelFont) {
		this.labelFont = labelFont;
	}

}