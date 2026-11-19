package model;

import java.util.ArrayList;

public class Piece {
	
	private String name;
	private String color;
	private String orientation;
	private boolean placed;
	private ArrayList<Block> blocks;
	
	public Piece(String name, String color, String orientation, boolean placed) {
		this.name = name;
		this.color = color;
		this.orientation = orientation;
		placed = false;
		blocks = new ArrayList<Block>();
	}
	
	public void newBlock(int row, int col) {
		blocks.add(new Block(row, col));
	}
	
	public void rotate() {
		
		for (Block block: blocks) {
			block.setRow(block.getCol());
			block.setCol(-block.getRow());
		}
		
		orientation = "90";
	}
	
	public void flip() {
		for(Block block: blocks) {
			block.setCol(-block.getCol());
		}
		
		orientation += " flipped";
	}
	
	public void setPlace() {
		placed = true;
	}
	
	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}


	public String getOrientation() {
		return orientation;
	}
	
	public void setPlaced(boolean placed) {
		this.placed = placed;
	}
	
	public boolean isPlaced() {
		return placed;
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	

}
