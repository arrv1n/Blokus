package model;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Player {

    private String name;
    private PlayerColour colour;
    private ArrayList<Piece> pieces;
    private int score;
    private int rotationflipLeft;
    private boolean isFirstMove;

    public Player(String name, PlayerColour colour) {
        this.name = name;
        this.colour = colour;
        this.pieces = new ArrayList<Piece>();
        this.score = 0;
        this.rotationflipLeft = 10;
        this.isFirstMove = true;
        loadPieces();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerColour getColour() {
        return colour;
    }

    public void setColour(PlayerColour colour) {
        this.colour = colour;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int points) {
        score += points;
    }

    public int getRotationflipLeft() {
        return rotationflipLeft;
    }

    public void setRotationflipLeft(int amount) {
        rotationflipLeft = Math.max(0, amount);
    }

    public boolean useTransformations(int amount) {
        if (amount < 0 || amount > rotationflipLeft) {
            return false;
        }

        rotationflipLeft -= amount;
        return true;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }

    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }
    
    private void loadPieces() {

        try (Scanner fileList = new Scanner(new File("text/PieceFile.txt"))) {

            while (fileList.hasNextLine()) {

                String fileName = fileList.nextLine().trim();

                if (fileName.isEmpty()) {
                    continue;
                }

                try (Scanner pieceFile = new Scanner(new File("files/" + fileName))) {

                    String name = pieceFile.nextLine();

                    int rows = Integer.parseInt(pieceFile.nextLine().trim());

                    Piece piece = new Piece(name, colour.name(), "0", false);

                    for (int row = 0; row < rows; row++) {

                        String line = pieceFile.nextLine();

                        for (int col = 0; col < line.length(); col++) {

                            if (line.charAt(col) == 'x') {
                                piece.newBlock(row, col);
                            }
                        }
                    }

                    pieces.add(piece);
                }
            }

        } catch (FileNotFoundException event) {
            throw new IllegalStateException("Unable to load pieces", event);
        }
    }
}
