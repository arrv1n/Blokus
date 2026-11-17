package View;

import javax.swing.*;

//This is an abstract class that will be used to create all other
//frames for the Blokus game
//It has a menu bar that will be present across all frames

public abstract class Frame extends JFrame{

	//instance variables
	//private ImageIcon speakerImage = new ImageIcon("png");
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenuItem homeMenuItem = new JMenuItem("Home");
	private JMenuItem instructionsMenuItem = new JMenuItem("Instructions");
	
	private JMenu settingsMenu = new JMenu("Settings");
	
	private JButton soundButton = new JButton();
	
	//constructor
	public Frame() {
		createGUIObjects();
		setUpFrame();
		
	}

	private void createGUIObjects() {
		createMenuBar();
		createButtons();
		
	}

	private void createMenuBar() {
		//set bounds of menu bar
		menuBar.setBounds(0, 0, 1134, 25);
		
		//add menu items to menu bar
		menuBar.add(homeMenuItem);
		menuBar.add(instructionsMenuItem);
		menuBar.add(settingsMenu);
		
		//add menubar to frame
		add(menuBar);
		
	}

	private void createButtons() {
		soundButton.setBounds(1134, 0, 50, 50);
		add(soundButton);
		
	}

	private void setUpFrame() {
		setSize(1200, 600);
		setLayout(null);
		
	}
	
}
