package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	private ImageIcon logoImage = new ImageIcon("images/blokusLogo.png");
	
	private JLabel logoLabel = new JLabel(logoImage);
	
	private Font buttonFont = new Font("Arial", Font.BOLD, 25);
	
	//Constructor
	public TitleFrame(ActionListener listener) {
		super(listener);
		super.setUpDesignLabels();
		setUpTitleFrame(listener);
		
	}

	private void setUpTitleFrame(ActionListener listener) {
		//create label for logo
		logoLabel.setBounds(285, 50, 600, 250);
		add(logoLabel);
		//create the buttons
		setUpButtons(listener);
		
		setTitle("Blokus");
		setVisible(true);
	}

	private void setUpButtons(ActionListener listener) {
		//set the background of each button
		buttons[0].setBackground(Color.decode("#0865bd"));
		buttons[1].setBackground(Color.decode("#69ab54"));
		buttons[2].setBackground(Color.decode("#c20d04"));
		
		int yPosition = 300; //Used to set bounds for buttons
		
		//Set the font and bounds of each button and add to frame
		for (JButton currentButton: buttons) {
			currentButton.setOpaque(true);
			currentButton.setBorderPainted(false);
			currentButton.setForeground(Color.WHITE);
			currentButton.setFont(buttonFont);
			currentButton.setBounds(375, yPosition, 400, 50);
			currentButton.addActionListener(listener);
			add(currentButton);
			//increase y position for next button
			yPosition += 75;
		}
		
	}
	
	public JButton[] getButtons() {
		return buttons;
	}

	public void setButtons(JButton[] buttons) {
		this.buttons = buttons;
	}

	public JLabel getLogoLabel() {
		return logoLabel;
	}

	public void setLogoLabel(JLabel logoLabel) {
		this.logoLabel = logoLabel;
	}
	
}
