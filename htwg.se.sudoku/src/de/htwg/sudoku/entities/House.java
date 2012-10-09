package de.htwg.sudoku.entities;

public class House {
	
	int size;
	int blockSize;
	Cell[] cells;

	public House(int size) {
		this.size = size;
		blockSize = (int) Math.sqrt(size);
		cells = new Cell[size];
	}
	
	public int getSize() {
		return size;
	}
	
	public Cell getCell(int index) {
		return cells[index];
	}
	
	public void setCell(int index, Cell cell){
		cells[index]=cell;
	}
		

}
