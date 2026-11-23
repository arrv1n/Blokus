package view;

import java.awt.Color;
import java.awt.MenuBar;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.*;
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
	
	private ImageIcon[] blokusDesignImages = {new ImageIcon("images/design1.png"),
			  								  new ImageIcon("images/design2.png"),
			  								  new ImageIcon("images/design3.png"),
			  								  new ImageIcon("images/design4.png")};
	
	private ArrayList<JLabel> blokusDesignLabels = new ArrayList<JLabel>();
	
	//constructor
	public Frame(ActionListener listener) {
		createGUIObjects(listener);
		setUpFrame();
		
	}

	private void createGUIObjects(ActionListener listener) {
		createMenuBar(listener);
		createButtons(listener);
		
	}

	private void createMenuBar(ActionListener listener) {
		//set bounds of menu bar
		menuBar.setBounds(0, 0, 1134, 25);
		
		//set background colours of menu items
		homeMenuItem.setBackground(Color.decode("#f7e599"));
		instructionsMenuItem.setBackground(Color.decode("#f7e599"));
		menuBar.setBackground(Color.decode("#f7e599"));
		
		//add actionlistener to menu item
		homeMenuItem.addActionListener(listener);
		instructionsMenuItem.addActionListener(listener);
		
		//add menu items to menu bar
		menuBar.add(homeMenuItem);
		menuBar.add(instructionsMenuItem);
		menuBar.add(settingsMenu);
		
		//add menubar to frame
		add(menuBar);
		
	}

	private void createButtons(ActionListener listener) {
		soundButton.setBackground(Color.decode("#f7e599"));
		soundButton.setBounds(1134, 0, 50, 50);
		soundButton.addActionListener(listener);
		add(soundButton);
		
	}
	
	private void setUpFrame() {
		setSize(1200, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.decode("#cadde8"));
		setLayout(null);
		
	}
	
	//This method sets up the design labels for hte background of a frame.
	//It is a protected method so that the frames that need it can use it.
	//Some frames won't have this, so this won't be called in the constructor 
	//of this class.
	protected void setUpDesignLabels() {		
		//add labels with images to the arraylist
		for (ImageIcon image: blokusDesignImages) {
			blokusDesignLabels.add(new JLabel(image));
		}
		
		//set bounds for each label
		blokusDesignLabels.get(0).setBounds(0, 23, 267, 272);
		blokusDesignLabels.get(1).setBounds(0, 297, 229, 266);
		blokusDesignLabels.get(2).setBounds(1041, 23, 159, 318);
		blokusDesignLabels.get(3).setBounds(905, 218, 281, 355);
		
		//add labels to frame
		for (JLabel label: blokusDesignLabels) {
			add(label);
		}
		
	}
	
	public JMenuBar getJMenuBar() {
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

	public ImageIcon getSpeakerImage() {
		return speakerImage;
	}

	public void setSpeakerImage(ImageIcon speakerImage) {
		this.speakerImage = speakerImage;
	}

	public ImageIcon[] getBlokusDesignImages() {
		return blokusDesignImages;
	}

	public void setBlokusDesignImages(ImageIcon[] blokusDesignImages) {
		this.blokusDesignImages = blokusDesignImages;
	}

	public ArrayList<JLabel> getBlokusDesignLabels() {
		return blokusDesignLabels;
	}

	public void setBlokusDesignLabels(ArrayList<JLabel> blokusDesignLabels) {
		this.blokusDesignLabels = blokusDesignLabels;
	}

}
