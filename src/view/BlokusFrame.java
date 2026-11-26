package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

import model.Block;
import model.Game;
import model.Piece;
import model.Player;

//This is the blokus frame
//It will be used to play blokus. It will feature the board, a panel to hold
//all the player's pieces, and a score board

public class BlokusFrame extends Frame {

	//instance variables
	private JLabel numRotAndFlipLabel = new JLabel();
	private JLabel playerTurnLabel = new JLabel();
	private JLabel resultsLabel = new JLabel();
	
	private JPanel cursorPanel = new JPanel();
	private JLabel[][] cursorBlocks = new JLabel[5][5];
	
	private BoardPanel boardPanel;
	private PiecePanel piecePanel;
	private PlayerPanel playerPanel;
	
	private Font labelFont = new Font("Arial", Font.BOLD, 20);
	
	private ImageIcon trophyImage = new ImageIcon("images/trophy.png");
	
	//constructor
	public BlokusFrame(ActionListener listener) {
		super(listener);
		setUpBlokusFrame();
		
	}
	
	private void setUpBlokusFrame() {
		setUpPanels();
		setUpLabels();
		setUpCursor();
		
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
		numRotAndFlipLabel.setText("Number of Rotation/Flip Left: 10");
		numRotAndFlipLabel.setForeground(Color.decode("#4287f5"));
		numRotAndFlipLabel.setFont(labelFont);
		numRotAndFlipLabel.setBounds(25, 25, 350, 50);
		add(numRotAndFlipLabel);
		
		playerTurnLabel.setText("Player 1's Turn!");
		playerTurnLabel.setForeground(Color.decode("#4287f5"));
		playerTurnLabel.setFont(labelFont);
		playerTurnLabel.setBounds(500, 25, 200, 50);
		add(playerTurnLabel);
		
	}

	//setup the objects used for piece cursor
	private void setUpCursor() {
		//Variables used for setting bounds of labels
		int xPosition = 0;
		int yPosition = 0;
		
		//Fill the cursorBlocks array with Jlabels and set their bounds
		for (int column = 0; column < cursorBlocks.length; column++) {
			for (int row = 0; row < cursorBlocks.length; row++)  {
				cursorBlocks[column][row] = new JLabel();
				cursorBlocks[column][row].setBounds(xPosition, yPosition, 20, 20);
				yPosition += 20;
				cursorPanel.add(cursorBlocks[column][row]);
			}
			xPosition += 20;
			yPosition = 0;
		}
		
		//setup the cursor panel
		cursorPanel.setSize(100, 100);
		cursorPanel.setOpaque(false);
		cursorPanel.setLayout(null);
		cursorPanel.setVisible(true);
		add(cursorPanel);
		
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
	
	public void updateTurn(Player player) {
	    playerTurnLabel.setText(player.getName() + "'s Turn!");
	}
	
	public void updateTransformations(int amount) {
	    numRotAndFlipLabel.setText("Number of Rotation/Flip Left: " + amount);
	    
	}
	
	//Show the results at the end of the game
	public int showResults(Game game) {
		//get the players in the game
		// ArrayList<Player> players = game.getPlayers();
		ArrayList<Player> players = new ArrayList<>(game.getPlayers());
		
		players = sortPlayerScore(players);
		
		//get the text used for optionpane
		String winner = players.getLast().getName();
		String leaderBoard = createLeaderBoard(players);
	
		//Change colour of optionPane background
		//source: https://stackoverflow.com/questions/9064943/how-to-change-background-color-of-joptionpane
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", Color.decode("#f7ca4f"));
		UI.put("Panel.background", Color.decode("#fffbb0"));
		
		//set up label that goes onto optionpane
		resultsLabel = new JLabel("<html>" + winner + " Wins!<br><br>"
				+ leaderBoard);
		resultsLabel.setFont(labelFont);

		//Create options for optionpane
		//source: https://stackoverflow.com/questions/44209347/creating-a-joptionpane-with-custom-actions-on-buttons
		String[] options = {"New Game", "Home"};
		Object option = null;
		option = options[0];
		
		//Create option pane
		//source: https://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html
		int startNew = JOptionPane.showOptionDialog(this, resultsLabel, "Game Results", 
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, trophyImage, options, option);
		
		//return the value of option selected for controller (0 = yes, 1 = no)
		return startNew;
		
	}

	private ArrayList<Player> sortPlayerScore(ArrayList<Player> players) {
		//sort players to find player with highest score (bubble sort)
		for (int size = players.size() - 1; size > 0; size--) {
			boolean swapped = false;
			for (int index = 0; index < size; index++) {
				if (players.get(index).getScore() > players.get(index + 1).getScore()) {
					swap(players, index, index + 1);
					swapped = true;
				}
			}
			if (swapped == false)
				break;
		}
		return players;
	}

	//Used for sorting method used in showResults
	private void swap(ArrayList<Player> players, int index, int index2) {
		Player temp = players.get(index);
		players.set(index, players.get(index2));
		players.set(index2, temp);
		
	}
	
	private String createLeaderBoard(ArrayList<Player> players) {
		
		//create leaderboard
		String leaderBoard = "";
		//Reverse the list so that winner is on top
		for (Player player: players.reversed()) {
			//add on to string
			leaderBoard = leaderBoard.concat(player.getName() + ": " + 
					Integer.toString(player.getScore()) + " Points<br>");
		}
		
		return leaderBoard;
	}

	//Change the piece following the cursor
	public void updateCursorImage(Piece piece) {
		//Clear any blocks on the cursor panel
		for (int column = 0; column < cursorBlocks.length; column++) {
			for (int row = 0; row < cursorBlocks.length; row++)  {
				cursorBlocks[column][row].setIcon(null);;
			}
		
		}
		
		//find the colour of the piece
		String colour = piece.getColor().toLowerCase();	
		
		//Change the colour of the tiles based on the piece
		for (Block block: piece.getBlocks()) {
			cursorBlocks[block.getCol()][block.getRow()].setIcon(
					new ImageIcon("images/" + colour + "Tile.png"));	
		}
		
	}
	
	//update the position of the piece cursor
	public void updateCursorPosition(int x, int y) {
		cursorPanel.setVisible(true);
		cursorPanel.setBounds(x - 15, y - 30, 100, 100);
	}
	
	//hide the image of the piece cursor
	public void hideCursorImage() {
		cursorPanel.setVisible(false);
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
