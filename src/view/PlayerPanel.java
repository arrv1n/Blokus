package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
import model.*;

//This is the player panel
//It will display the score of all players

public class PlayerPanel extends JPanel implements Update{
	
	//instance variables
	private JLabel[] nameLabels = new JLabel[4];
	private JLabel[] scoreLabels = new JLabel[4];
	
	private Font labelFont = new Font("Arial", Font.BOLD, 20);
	
	public PlayerPanel() {
		super();
		setUpPlayerPanel();
		
	}

	private void setUpPlayerPanel() {
		setUpLabels();
		
		setOpaque(false);
		setLayout(null);
		//setSize(400, 100);
		
	}

	private void setUpLabels() {
		//create the labels
		for (int index = 0; index < nameLabels.length; index++) {
			nameLabels[index] = new JLabel("Player " + (index + 1));
			scoreLabels[index] = new JLabel("0");
		}
		
		//this is used to set the bounds of the labels 
		int yPosition = 0;
	
		//set up labels' visual
		for (int index = 0; index < nameLabels.length; index++) {
			
			nameLabels[index].setForeground(Color.WHITE);
			nameLabels[index].setOpaque(true);
			
			scoreLabels[index].setForeground(Color.BLACK);
			scoreLabels[index].setBackground(Color.WHITE);
			scoreLabels[index].setOpaque(true);
			
			nameLabels[index].setFont(labelFont);
			scoreLabels[index].setFont(labelFont);
			
		}
		
		nameLabels[0].setBackground(Color.BLUE);
		nameLabels[1].setBackground(Color.RED);
		nameLabels[2].setBackground(Color.GREEN);
		nameLabels[3].setBackground(Color.YELLOW);
		
		//set bounds labels and add them (it adds them by row. 2 in a row)
		for (int index = 0; index < nameLabels.length; index += 2) {
			nameLabels[index].setBounds(0, yPosition, 100, 50);
			nameLabels[index + 1].setBounds(250, yPosition, 100, 50);
			add(nameLabels[index]);
			add(nameLabels[index + 1]);
			
			scoreLabels[index].setBounds(100, yPosition, 50, 50);
			scoreLabels[index + 1].setBounds(350, yPosition, 50, 50);
			add(scoreLabels[index]);
			add(scoreLabels[index + 1]);
			
			//increment y position for next
			yPosition += 50;
		}
				
	}
	
	//update the visuals of the panels
	@Override
	public void update(Game game) {
		//get the player objects from game class
		ArrayList<Player> players = game.getPlayers();
		
		int index = 0; //used when changing labels
		
		//set the text of the player's score label to their new score
		for (Player player: players) {
			scoreLabels[index].setText(Integer.toString(player.getScore()));
			
			index++;
		}
		
	}
	
}