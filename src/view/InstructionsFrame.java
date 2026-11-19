package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

//This is the instructions frame
//It will have the instructions on how to play blokus

public class InstructionsFrame extends Frame{

	//fields and objects
	private JLabel titleLabel = new JLabel("Instructions");
	
	private File instructions = new File("text/instructions.txt");
	private JTextArea instructionsText = new JTextArea();
	
	private JScrollPane instructionsScroll = new JScrollPane(instructionsText);
	
	//constructor
	public InstructionsFrame(ActionListener listener) {
		super(listener);
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
		instructionsText.setOpaque(false);
		
		//read text file and display the instructions on the text area
		displayInstructions();
		
		//Instructions panel
		instructionsScroll.setBounds(300, 125, 600, 400);
		instructionsScroll.setBorder(null);
		add(instructionsScroll);
		
	}

	private void displayInstructions() {
		
		try {
			//create scanner
			Scanner inputFile = new Scanner(instructions);
			//repeat while there is still a next line in the .txt file
			while(inputFile.hasNext()) {
				//add the text to the instructions text area
				instructionsText.append(inputFile.nextLine() + "\n");
			}
			inputFile.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}

	public JTextArea getInstructionsText() {
		return instructionsText;
	}

	public void setInstructionsText(JTextArea instructionsText) {
		this.instructionsText = instructionsText;
	}

	public JScrollPane getInstructionsScroll() {
		return instructionsScroll;
	}

	public void setInstructionsScroll(JScrollPane instructionsScroll) {
		this.instructionsScroll = instructionsScroll;
	}
	
}
