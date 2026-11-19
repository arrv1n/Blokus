package view;
import java.awt.Color;
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
	private ImageIcon[] blokusDesignImages = {new ImageIcon("images/design1.png"),
											  new ImageIcon("images/design2.png"),
											  new ImageIcon("images/design3.png"),
											  new ImageIcon("images/design4.png")};
	
	private JLabel logoLabel = new JLabel(logoImage);
	private ArrayList<JLabel> blokusDesignLabels = new ArrayList<JLabel>();
	
	//Constructor
	public TitleFrame(ActionListener listener) {
		super(listener);
		setUpTitleFrame(listener);
		
	}

	private void setUpTitleFrame(ActionListener listener) {
		//create the buttons
		setUpButtons(listener);
		//create labels for design
		setUpLabels();
		
		setTitle("Blokus");
		getContentPane().setBackground(Color.decode("#cadde8"));
		setVisible(true);
		
	}

	private void setUpButtons(ActionListener listener) {
		int yPosition = 300; //Used to set bounds for buttons
		
		//Set the bounds of each button and add to frame
		for (JButton currentButton: buttons) {
			currentButton.setBounds(375, yPosition, 400, 50);
			currentButton.addActionListener(listener);
			add(currentButton);
			//increase y position for next button
			yPosition += 75;
		}
		
	}

	private void setUpLabels() {
		logoLabel.setBounds(285, 50, 600, 250);
		add(logoLabel);
		
		//add labels with images to the arraylist
		for (ImageIcon image: blokusDesignImages) {
			blokusDesignLabels.add(new JLabel(image));
		}
		
		//set bounds for each label
		blokusDesignLabels.get(0).setBounds(0, 25, 227, 222);
		blokusDesignLabels.get(1).setBounds(0, 303, 205, 257);
		blokusDesignLabels.get(2).setBounds(1077, 25, 123, 287);
		blokusDesignLabels.get(3).setBounds(893, 213, 287, 347);
		
		//add labels to frame
		for (JLabel label: blokusDesignLabels) {
			add(label);
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

	public ArrayList<JLabel> getBlokusDesignLabels() {
		return blokusDesignLabels;
	}

	public void setBlokusDesignLabels(ArrayList<JLabel> blokusDesignLabels) {
		this.blokusDesignLabels = blokusDesignLabels;
	}

}