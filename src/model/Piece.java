/*
Name: Sean Lee
Title: Blockus - Piece Class

Description:
The peice class represents one Blockus piece. Each containing 1-5 blocks. 
The peice class stores a player's peices its colour orientation and weather or not it's placed. This
class also contains the methods to rotate and flip a peice.

*/

package model;

import java.util.ArrayList;

public class Piece {

	//fields
	private String name;
	private String color;
	private String orientation;
	private boolean placed;
	private ArrayList<Block> blocks;

	//constructors
	public Piece(String name, String color, String orientation, boolean placed) {
		this.name = name;
		this.color = color;
		this.orientation = orientation;
		this.placed = placed;
		blocks = new ArrayList<Block>();
	}

	//method to create a new block and adding it to the peice
	public void newBlock(int row, int col) {
		blocks.add(new Block(row, col));
	}

	//method to rotate the peice by going through each block within the peice
	//and chaning the coordinates of each block.
	public void rotate() {

		for (Block block: blocks) {
			int oldRow = block.getRow();
	        int oldCol = block.getCol();
	        block.setRow(oldCol);
	        block.setCol(-oldRow);
		}
		
		orientation = "90";
	}

	//method to flip the peice by changing the coordinates of each block
	public void flip() {
		for(Block block: blocks) {
			block.setCol(-block.getCol());
		}
		
		orientation += " flipped";
	}

	//getter for peice name
	public String getName() {
		return name;
	}

	//getter for peice colour
	public String getColor() {
		return color;
	}

	//getter for the orientation of the peice
	public String getOrientation() {
		return orientation;
	}

	//Method to set a peice down 
	public void setPlaced(boolean placed) {
		this.placed = placed;
	}

	//method to check if the peice is placed
	public boolean isPlaced() {
		return placed;
	}

	//method to get the blocks in the peice 
	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	//method to get the number of square/blocks in the peice
	public int size() {
	    return blocks.size();
	}
	

}
