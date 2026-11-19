package view;

import java.awt.Color;
import java.awt.MenuBar;
import java.awt.event.ActionListener;

import javax.swing.*;

//This is an abstract class that will be used to create all other
//frames for the Blokus game
//It has a menu bar that will be present across all frames

public abstract class Frame extends JFrame{

	//instance variables
	private ImageIcon speakerImage = new ImageIcon("images/soundIcon.png");
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenuItem homeMenuItem = new JMenuItem("Home");
	private JMenuItem instructionsMenuItem = new JMenuItem("Instructions");
	
	private JMenu settingsMenu = new JMenu("Settings");
	
	private JButton soundButton = new JButton(speakerImage);
	
	//constructor
	public Frame(ActionListener listener) {
		createGUIObjects(listener);
		setUpFrame();
		
	}

	private void createGUIObjects(ActionListener listener) {
		createMenuBar(listener);
		createButtons();
		
	}

	private void createMenuBar(ActionListener listener) {
		//set bounds of menu bar
		menuBar.setBounds(0, 0, 1134, 25);
		
		//set background colours of menu items
		homeMenuItem.setBackground(Color.ORANGE);
		instructionsMenuItem.setBackground(Color.ORANGE);
		menuBar.setBackground(Color.ORANGE);
		
		//add menu items to menu bar
		menuBar.add(homeMenuItem);
		menuBar.add(instructionsMenuItem);
		menuBar.add(settingsMenu);
		
		//add actionlistener to menu items
		homeMenuItem.addActionListener(listener);
		instructionsMenuItem.addActionListener(listener);
		settingsMenu.addActionListener(listener);
		
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
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JMenuItem getHomeMenuItem() {
		return homeMenuItem;
	}

	public void setHomeMenuItem(JMenuItem homeMenuItem) {
		this.homeMenuItem = homeMenuItem;
	}

	public JMenuItem getInstructionsMenuItem() {
		return instructionsMenuItem;
	}

	public void setInstructionsMenuItem(JMenuItem instructionsMenuItem) {
		this.instructionsMenuItem = instructionsMenuItem;
	}

	public JMenu getSettingsMenu() {
		return settingsMenu;
	}

	public void setSettingsMenu(JMenu settingsMenu) {
		this.settingsMenu = settingsMenu;
	}

	public JButton getSoundButton() {
		return soundButton;
	}

	public void setSoundButton(JButton soundButton) {
		this.soundButton = soundButton;
	}

}
