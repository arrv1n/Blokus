package View;

import javax.swing.*;

//This is the player panel
//It will display the score of all players

public class PlayerPanel extends JPanel{
	
	//instance variables
	private JLabel[] nameLabels = new JLabel[4];
	private JLabel[] scoreLabels = new JLabel[4];
	
	public PlayerPanel() {
		super();
		setUpPlayerPanel();
		
	}

	private void setUpPlayerPanel() {
		setUpLabels();
		
		setLayout(null);
		setSize(400, 100);
		
	}

	private void setUpLabels() {
		//create the labels
		for (int index = 0; index < nameLabels.length; index++) {
			nameLabels[index] = new JLabel("Player " + (index + 1));
			scoreLabels[index] = new JLabel("0");
		}
		
		//this is used to set the bounds of the labels 
		int yPosition = 0;
		
		//set bounds for labels and add them (it adds them by row. 2 in a row)
		for (int index = 0; index < nameLabels.length; index += 2) {
			nameLabels[index].setBounds(0, yPosition, 50, 50);
			nameLabels[index + 1].setBounds(300, yPosition, 50, 50);
			add(nameLabels[index]);
			add(nameLabels[index + 1]);
			
			scoreLabels[index].setBounds(50, yPosition, 25, 50);
			scoreLabels[index + 1].setBounds(350, yPosition, 25, 50);
			add(scoreLabels[index]);
			add(scoreLabels[index + 1]);
			
			//increment y position for next
			yPosition += 50;
		}
				
	}
	
	private void updateScore() {
		
	}
	
}
