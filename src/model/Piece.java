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
		this.placed = placed;
		blocks = new ArrayList<Block>();
	}
	
	public void newBlock(int row, int col) {
		blocks.add(new Block(row, col));
	}
	
	public void rotate() {
		
		for (Block block: blocks) {
			int oldRow = block.getRow();
	        int oldCol = block.getCol();
	        block.setRow(oldCol);
	        block.setCol(-oldRow);
		}
		
		orientation = "90";
	}
	
	public void flip() {
		for(Block block: blocks) {
			block.setCol(-block.getCol());
		}
		
		orientation += " flipped";
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

	public int size() {
	    return blocks.size();
	}
	

}
