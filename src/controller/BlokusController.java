package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import model.*;
import view.BlokusFrame;
import view.BoardPanel;
import view.InstructionsFrame;
import view.PiecePanel;
import view.TitleFrame;

public class BlokusController implements ActionListener, MouseListener, KeyListener, MouseMotionListener {

    private Game game;

    private TitleFrame titleFrame;
    private InstructionsFrame instructionsFrame;
    private BlokusFrame blokusFrame;

    private Piece chosenPiece;
    private JLabel hoveredTile;

    private Clip bgm;
    private boolean soundOn;

    // 2. Start the game by displaying the title screen
    public BlokusController() {
    	
        titleFrame = new TitleFrame(this);
        titleFrame.setVisible(true);
        initializeSound();
        updateSoundButtons();
        
    }

    // 3. Handle button and menu events
    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        JButton[] buttons = titleFrame.getButtons();

        if (source == buttons[0]) {
            startNewGame();

        } else if (source == buttons[1]) {
        	loadGame();
        	
        } else if (source == buttons[2] || isInstructionsEvent(source)) {
            showInstructions();

        } else if (isHomeEvent(source)) {
            showTitleScreen();

        } else if (isSoundEvent(source)) {
            toggleSound();
        }

    }
    
    public void loadGame() {
    	
    }

    private boolean isHomeEvent(Object source) {
        return source == titleFrame.getHomeMenuItem() || (instructionsFrame != null && source == instructionsFrame.getHomeMenuItem()) || (blokusFrame != null && source == blokusFrame.getHomeMenuItem());
    }

    private boolean isInstructionsEvent(Object source) {
        return source == titleFrame.getInstructionsMenuItem() || (instructionsFrame != null && source == instructionsFrame.getInstructionsMenuItem()) || (blokusFrame != null && source == blokusFrame.getInstructionsMenuItem());
    }

    private boolean isSoundEvent(Object source) {

        return source == titleFrame.getSoundButton() || (instructionsFrame != null && source == instructionsFrame.getSoundButton()) || (blokusFrame != null && source == blokusFrame.getSoundButton());
    }

    // 4. Create the four players and a new Game
    public void startNewGame() {
        ArrayList<Player> players = new ArrayList<Player>();

        try {
            players.add(new Player("Player 1", PlayerColour.BLUE));
//            players.add(new Player("Player 2", PlayerColour.RED));
//            players.add(new Player("Player 3", PlayerColour.YELLOW));
//            players.add(new Player("Player 4", PlayerColour.GREEN));
            players.add(new AIPlayer("CPU 1", PlayerColour.RED, true));
            players.add(new AIPlayer("CPU 2", PlayerColour.YELLOW, true));
            players.add(new AIPlayer("CPU 3", PlayerColour.GREEN, true));
        } catch (RuntimeException error) {
            JOptionPane.showMessageDialog(titleFrame,
                    "Unable to load pieces: " + error.getMessage());
            return;
        }

        game = new Game(players);
        chosenPiece = null;
        hoveredTile = null;

        openGameFrame();
    }

    private void openGameFrame() {

    	titleFrame.setVisible(false);
        blokusFrame = new BlokusFrame(this);
        blokusFrame.setVisible(true);
        
        blokusFrame.addKeyListener(this);
        blokusFrame.addMouseMotionListener(this);
        
        // add link 
        blokusFrame.requestFocusInWindow();
        
        connectGameListeners();
        nextTurn();
        updateSoundButtons();
        
    }
    

    // 5. Connect each JLabel to this Controller
    private void connectGameListeners() {
        BoardPanel boardPanel = blokusFrame.getBoardPanel();
        PiecePanel piecePanel = blokusFrame.getPiecePanel();

        for (JLabel[] tiles : boardPanel.getBoardLabels()) {
            for (JLabel tile : tiles) {
                tile.addMouseListener(this);
            }
        }

        for (JLabel piece : piecePanel.getPlayerPieces()) {
            piece.addMouseListener(this);
        }
    }

    // 6. Handle clicks on pieces and board tiles
    @Override
    public void mouseClicked(MouseEvent event) {
        if (game == null || game.isGameOver() || blokusFrame == null || !SwingUtilities.isLeftMouseButton(event)) {
            return;
        }

        if (!(event.getSource() instanceof JLabel)) {
            return;
        }

        JLabel label = (JLabel) event.getSource();
        PiecePanel piecePanel = blokusFrame.getPiecePanel();
        int index = piecePanel.getPlayerPieces().indexOf(label);

        // A piece label and a board label have different parents.
        if (index >= 0) {
            Piece piece = game.getCurrentPlayer().getPieces().get(index);
            selectPiece(piece);

        } else if (label.getParent() == blokusFrame.getBoardPanel()) {
            int tileSize = 20;
            int row = label.getY() / tileSize;
            int col = label.getX() / tileSize;

            handleBoardClick(row, col);
        }
    }

    public void selectPiece(Piece piece) {
        if (game == null || game.isGameOver() || piece == null || piece.isPlaced()) {
            return;
        }

        int index = game.getCurrentPlayer().getPieces().indexOf(piece);
        if (index < 0) {
            return;
        }

        chosenPiece = piece;
        updateGhostPiece();
        blokusFrame.updateCursorImage(chosenPiece);
    }

    // 7. Show and clear the ghost piece
    @Override
    public void mouseEntered(MouseEvent event) {
        if (blokusFrame == null || !(event.getSource() instanceof JLabel)) {
            return;
        }

        blokusFrame.hideCursorImage();
        
        JLabel label = (JLabel) event.getSource();

        if (label.getParent() == blokusFrame.getBoardPanel()) {
            hoveredTile = label;
            updateGhostPiece();
        }
    }

    @Override
    public void mouseExited(MouseEvent event) {
        if (blokusFrame != null && event.getSource() == hoveredTile) {
            hoveredTile = null;
            blokusFrame.updateBoard(game);
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }

    private void updateGhostPiece() {
        if (game == null || blokusFrame == null) {
            return;
        }

        // Restore the real board before drawing a new preview.
        blokusFrame.updateBoard(game);

        if (chosenPiece == null || hoveredTile == null || game.isGameOver()) {
            return;
        }

        BoardPanel boardPanel = blokusFrame.getBoardPanel();
        Player player = game.getCurrentPlayer();

        int x = hoveredTile.getX();
        int y = hoveredTile.getY();
        int row = y / 20;
        int col = x / 20;

        // boolean valid = game.getBoard().isValidMove(chosenPiece, row, col, player);
        // boardPanel.showGhostPiece(chosenPiece, x, y, player.getColour(), valid);
        
        boardPanel.showGhostPiece(chosenPiece, x, y);
    }

    // 8. Register R, F
    // Handle keyboard events
    @Override
    public void keyPressed(KeyEvent event) {

        switch (event.getKeyCode()) {

            case KeyEvent.VK_R:
                rotatePiece();
                break;

            case KeyEvent.VK_F:
                flipPiece();
                break;
                
            case KeyEvent.VK_P:
                nextTurn();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    @Override
    public void keyTyped(KeyEvent event) {

    }

    public void rotatePiece() {
        if (game == null || game.isGameOver() || chosenPiece == null) {
            return;
        }

        Player player = game.getCurrentPlayer();

        if (player.useTransformations(1)) {
            chosenPiece.rotate();
            blokusFrame.updateTransformations(player.getRotationflipLeft());
            updateGhostPiece();
        }
    }

    public void flipPiece() {
        if (game == null || game.isGameOver() || chosenPiece == null) {
            return;
        }

        Player player = game.getCurrentPlayer();

        if (player.useTransformations(1)) {
            chosenPiece.flip();
            blokusFrame.updateTransformations(player.getRotationflipLeft());
            updateGhostPiece();
        }
    }

//    private void clearSelection() {
//        chosenPiece = null;
//
//        if (blokusFrame != null) {
//            blokusFrame.getPiecePanel().highlightPiece(-1);
//            blokusFrame.updateBoard(game);
//        }
//    }

    // 9. Submit a move through the Game model
    public void handleBoardClick(int row, int col) {
        if (game == null || game.isGameOver() || chosenPiece == null) {
            return;
        }

        if (game.placePiece(chosenPiece, row, col)) {
            nextTurn();
        }
    }

    // 10. Pass to the next turn
    public void nextTurn() {
    	
        if (game == null || game.isGameOver() || game.getCurrentPlayer() instanceof AIPlayer) {
            return;
        }
        
        chosenPiece = null;
        hoveredTile = null;

        game.nextPlayer();
        
        
        while (game.getCurrentPlayer() instanceof AIPlayer) {
            AIPlayer cpu = (AIPlayer) game.getCurrentPlayer();

            if (cpu.findMove(game.getBoard())) {
                Piece piece = cpu.getBestPiece();
                int row = cpu.getBestRow();
                int col = cpu.getBestCol();

                cpu.applyBestOrientation();

                if (!game.placePiece(piece, row, col)) {
                    JOptionPane.showMessageDialog(blokusFrame, "The CPU move could not be placed.");
                    return;
                }

                cpu.useTransformations(cpu.getbestTransformation());
            }

            // A successful CPU move or a CPU pass ends the turn.
            game.nextPlayer();
        }
        
        blokusFrame.updateBoard(game);
        blokusFrame.updatePieces(game);
        blokusFrame.updatePlayer(game);
        blokusFrame.updateTurn(game.getCurrentPlayer());
        blokusFrame.updateTransformations(game.getCurrentPlayer().getRotationflipLeft());
        
        checkGameOver();
        
    }


    // 11. End the game when none of the players can move
    public void checkGameOver() {
        if (game == null || game.isGameOver()) {
            return;
        }

        if (!game.hasAnyLegalMove()) {
            game.endGame();
            blokusFrame.updateBoard(game);

            int choice = blokusFrame.showResults(game);

            if (choice == 0) {
                startNewGame();
            } else {
                showTitleScreen();
            }
        }
    }

    // 12. Display the title or instructions screen
    public void showTitleScreen() {
        if (blokusFrame != null) {
            blokusFrame.dispose();
            blokusFrame = null;
        }

        if (instructionsFrame != null) {
            instructionsFrame.setVisible(false);
        }

        game = null;
        chosenPiece = null;
        hoveredTile = null;
        titleFrame.setVisible(true);
    }

    public void showInstructions() {
        if (instructionsFrame == null) {
            instructionsFrame = new InstructionsFrame(this);
        }

        titleFrame.setVisible(false);
        if (blokusFrame != null) {
            blokusFrame.setVisible(false);
        }

        updateSoundButtons();
        instructionsFrame.setVisible(true);
    }

    // 13. Optional sound
    private void initializeSound() {
        File file = new File("music/bgm.wav");
        if (!file.isFile()) {
            return;
        }

        try (AudioInputStream audio = AudioSystem.getAudioInputStream(file)) {
            bgm = AudioSystem.getClip();
            bgm.open(audio);
            bgm.loop(Clip.LOOP_CONTINUOUSLY);
            soundOn = true;

        } catch (Exception error) {
            if (bgm != null) {
                bgm.close();
                bgm = null;
            }
            soundOn = false;
            System.out.println("Background music unavailable.");
        }
    }

    public void toggleSound() {

        soundOn = !soundOn;

        if (bgm != null) {

            if (soundOn) {
                bgm.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                bgm.stop();
            }
        }

        updateSoundButtons();
    }

    private void updateSoundButtons() {
        titleFrame.updateSoundIcon(soundOn);
        if (instructionsFrame != null) {
            instructionsFrame.updateSoundIcon(soundOn);
        }
        if (blokusFrame != null) {
            blokusFrame.updateSoundIcon(soundOn);
        }
    }

    public Game getGame() {
        return game;
    }

    public Piece getChosenPiece() {
        return chosenPiece;
    }

    @Override
	public void mouseDragged(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}
    
	@Override
	public void mouseMoved(MouseEvent event) {
		if (chosenPiece != null) {
			int index = game.getCurrentPlayer().getPieces().indexOf(chosenPiece);
			blokusFrame.updateCursorPosition(event.getX(), event.getY());
		}
		else if (chosenPiece == null) {
			blokusFrame.hideCursorImage();
		}
		
	}
}
