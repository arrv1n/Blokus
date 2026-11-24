package model;

public class Block {
	
	private int row;
	private int col;
	
	public Block(int row,int col){
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		if (row < 0 || row > 19)
			System.out.println("Invalid col Spot");
		else
			this.row = row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setCol(int col) {
		if (col < 0 || col > 19)
			System.out.println("Invalid row Spot");
		else
			this.col = col;
	}
}
