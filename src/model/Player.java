package model;

public class Player {
	
	String name;
	PlayerColour color;
	ArrayList<Piece> pieces;
	int score;
	int rotationflipLeft;
	
	public Player() {
		
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

	public int getRotationflipLeft() {
		return rotationflipLeft;
	}

	public void setRotationflipLeft(int rotationflipLeft) {
		this.rotationflipLeft = rotationflipLeft;
	}

	public void removePiece() {
		
		
	}
	
	public int addScore() {
		
		
		
	}

}
