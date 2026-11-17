package View;

import java.awt.Color;

import javax.swing.*;

//This is the instructions frame
//It will have the instructions on how to play blokus

public class InstructionsFrame extends Frame{

	//fields and objects
	private JLabel titleLabel = new JLabel("Instructions");
	
	private JTextArea instructionsText = new JTextArea("e\ne\ne\ne\ne\ne\ne\ne\n\n\n\n\ne\ne\ne\ne\ne\ne\ne\n\ne\ne\ne\ne\n\n\n\n\n\n\n\n\n\nee");
	
	private JScrollPane instructionsScroll = new JScrollPane(instructionsText);
	
	//constructor
	public InstructionsFrame() {
		super();
		setUpInstructionsFrame();
		
	}
	
	private void setUpInstructionsFrame() {
		//Label
		titleLabel.setBounds(500, 50, 200, 100);
		add(titleLabel);
		
		setUpInstructionsText();
		
		setTitle("Blokus - Instructions");
		setVisible(true);
		
	}

	private void setUpInstructionsText() {
		//Instructions text area
		instructionsText.setEditable(false);
		
		//Instructions panel
		instructionsScroll.setBounds(300, 125, 600, 400);
		add(instructionsScroll);
				
		
	}
	
}