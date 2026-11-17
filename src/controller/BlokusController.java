package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Player;
import view.BlokusFrame;
import view.InstructionsFrame;
import view.TitleFrame;

public class BlokusController implements ActionListener{
	
	private Game game;
	
	private TitleFrame titleFrame;
    private BlokusFrame blokusFrame;
    private InstructionsFrame instructionsFrame;
    private Piece chosenPiece;
	
	public BlokusController() {
		
		titleFrame = new TitleFrame();
		titleFrame.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		switch(event.getSource()) {
		
			case "new_game":
	            startNewGame();
	            break;
	
	        case "resume":
	            resumeGame();
	            break;
	
	        case "instructions":
	            showInstructions();
	            break;
	
	        case "home":
	            showTitleScreen();
	            break;
	
	        case "save":
	            saveGame();
	            break;
	
		}
	}
	
	public void startNewGame() {
		
		
	}
	
	public void resumeGame() {
		
		
	}
	
	public void saveGame() {
		
		
	}
	
	public void showTitleScreen() {
		titleFrame.setVisible(true);
	}
	
	public void showInstructions() {
		instructionsFrame = new InstructionsFrame();
		instructionsFrame.setVisible(true);
	}
	
	public void rotatePiece() {
		
		Player player = game.getPlayer();
		if (player.getRotationflipLeft() <= 0) return;
		
	
		
	}
	
	public void flipPiece() {
		
		
	}
	
	public void isGameOver() {
		
		
		
	}
	
}
