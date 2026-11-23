package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.*;

import model.Game;

//This is the piece panel
//It will hold and display the pieces the player currently owns
//and can use

public class PiecePanel extends JPanel implements Update{
	
	//instance variables
	private ArrayList<JLabel> playerPieces = new ArrayList<JLabel>();
	private ArrayList<ImageIcon> pieceImages = new ArrayList<ImageIcon>();
	
	private File pieceFile = new File("text/PieceFile.txt");
	
	
	public PiecePanel() {
		super();
		setUpPiecePanel();
		
		
	}

	private void setUpPiecePanel() {
		setUpLabels();
		
		//set layout of panel to grid
		//source: https://docs.oracle.com/javase/tutorial/uiswing/layout/using.html
		setLayout(new GridLayout(2, 10));
		
	}

	private void setUpLabels() {
		//Fill piece array list
		fillPieceArray();
		
		int labelIndex = 0;//Track image index file
		
		//set bounds and add to frame
		for (ImageIcon image: pieceImages) {
			playerPieces.add(new JLabel(image)); //create label and add to array list
			add(playerPieces.get(labelIndex)); //add label to frame
			
			labelIndex++; //increment index
			
		}
		
	}

	private void fillPieceArray() {
		//Read text file and fill piece array with images
		try {
			Scanner inputFile = new Scanner(pieceFile);
			//use delimiter to only get name of pieces
			inputFile.useDelimiter(".txt\r\n");
			//repeat while there is still a next line in the .txt file
			while(inputFile.hasNext()) {
				//add new image to image array
				pieceImages.add(new ImageIcon("images2/blue" + inputFile.next() + ".png"));
			}
			inputFile.close();
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Game game) {
		
		
	}
	
}
