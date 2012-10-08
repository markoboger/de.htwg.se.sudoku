package de.htwg.sudoku.entities;

import java.util.List;

public class House {
	
	List<Cell> cells;
	
	public House(List<Cell> cells) {
		this.cells=cells;	
	}
	
	public int getSize() {
		return cells.size();
	}
	
	public Cell getCell(int index) {
		return cells.get(index);
	}

}
