package model;

import java.util.ArrayList;

public class Player {

    private String name;
    private PlayerColour color;
    private ArrayList<Piece> pieces;
    private int score;
    private int rotationflipLeft;
    private boolean isFirstMove;

    public Player(String name, PlayerColour color) {
        this.name = name;
        this.color = color;
        this.pieces = new ArrayList<Piece>();
        this.score = 0;
        this.rotationflipLeft = 10;
        this.isFirstMove = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerColour getColor() {
        return color;
    }

    public void setColor(PlayerColour color) {
        this.color = color;
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
}
