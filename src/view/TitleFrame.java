package View;
import java.util.*;
import javax.swing.*;

//This class is the title frame for the Blokus game
//It features buttons to start the game, load a saved game, or 
//view the instructions

public class TitleFrame extends Frame{
	
	//instance variables
	//buttons
	//indexes: 0 - New game button, 1 - resumeButton, 2 - instructionButton
	private JButton[] buttons = {new JButton("New Game"),
						 new JButton("Resume"),
						 new JButton("Instructions")};
	
	private JLabel logoLabel = new JLabel();
	private ArrayList<JLabel> blokusDesignLabels = new ArrayList<JLabel>();
	
	//Constructor
	public TitleFrame() {
		super();
		setUpTitleFrame();
		
	}

	private void setUpTitleFrame() {
		//create the buttons
		setUpButtons();
		//create labels for design
		setUpLabels();
		
		PlayerPanel playerPanel = new PlayerPanel();
		playerPanel.setBounds(0, 100, 400, 100);
		add(playerPanel);
		
		setTitle("Blokus");
		setVisible(true);
		
	}

	private void setUpButtons() {
		int yPosition = 300; //Used to set bounds for buttons
		
		//Set the bounds of each button and add to frame
		for (JButton currentButton: buttons) {
			currentButton.setBounds(375, yPosition, 400, 50);
			add(currentButton);
			//increase y position for next button
			yPosition += 75;
		}
		
	}

	private void setUpLabels() {
		
	}

}