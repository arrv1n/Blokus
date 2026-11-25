package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

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

public class BlokusController implements ActionListener, MouseListener, KeyListener {

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
        	resumeGame();
        	
        } else if (source == buttons[2] || isInstructionsEvent(source)) {
            showInstructions();

        } else if (isHomeEvent(source)) {
            showTitleScreen();

        } else if (isSoundEvent(source)) {
            toggleSound();
        }
//        } else if (blokusFrame != null && source == blokusFrame.getPassButton()) {
//            passTurn();
//        }
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
            players.add(new Player("Player 2", PlayerColour.RED));
            players.add(new Player("Player 3", PlayerColour.YELLOW));
            players.add(new Player("Player 4", PlayerColour.GREEN));
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
        
        connectGameListeners();
        refreshGameView();
        updateSoundButtons();
        
    }
    
    public void resumeGame() {
    	
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
//        blokusFrame.getPiecePanel().highlightPiece(index);
        updateGhostPiece();
    }

    // 7. Show and clear the ghost piece
    @Override
    public void mouseEntered(MouseEvent event) {
        if (blokusFrame == null || !(event.getSource() instanceof JLabel)) {
            return;
        }

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

    // 10. Pass, advance the turn and refresh every panel
    public void passTurn() {
        if (game == null || game.isGameOver()) {
            return;
        }

        if (game.hasPossibleMove(game.getCurrentPlayer())) {
            JOptionPane.showMessageDialog(blokusFrame, "You still have a legal move.");
            return;
        }

        nextTurn();
    }

    private void nextTurn() {
        chosenPiece = null;
        hoveredTile = null;

        game.nextPlayer();
        refreshGameView();
        checkGameOver();
    }

    private void refreshGameView() {
        if (game == null || blokusFrame == null) {
            return;
        }

        blokusFrame.updateBoard(game);
        blokusFrame.updatePieces(game);
        blokusFrame.updatePlayer(game);
        blokusFrame.updateTurn(game.getCurrentPlayer());
        blokusFrame.updateTransformations(game.getCurrentPlayer().getRotationflipLeft());
    }

    // 11. End the game when none of the players can move
    public void checkGameOver() {
        if (game == null || game.isGameOver()) {
            return;
        }

        if (!game.hasAnyLegalMove()) {
            game.endGame();
            blokusFrame.updateBoard(game);
            // blokusFrame.getPassButton().setEnabled(false);

            int choice = blokusFrame.showResults();

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
        File file = new File("audio/bgm.wav");
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
}
