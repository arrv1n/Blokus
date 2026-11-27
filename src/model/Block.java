/*
Name: Sean Lee
Title: Blockus - Block Class

Description:
This the is the block class. Each block represents one square in a block us peice. Each block stores its own row and col
to be used and placed on the game board.
*/

package model;

public class Block {

	//fields
	private int row;
	private int col;

	//constructor
	public Block(int row,int col){
		this.row = row;
		this.col = col;
	}

	//getters for row
	public int getRow() {
		return row;
	}

	//setters for row
	public void setRow(int row) {
			this.row = row;
	}

	//getters for column
	public int getCol() {
		return col;
	}

	//settings for column
	public void setCol(int col) {
			this.col = col;
	}
	
}
