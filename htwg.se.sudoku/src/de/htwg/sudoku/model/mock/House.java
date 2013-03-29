package de.htwg.sudoku.model.mock;

import de.htwg.sudoku.model.AbstractHouse;



public class House extends AbstractHouse{

	public House(int size) {
		this.size = size;
		blockSize = (int) Math.sqrt(size);
		cells = new Cell[size];
	}

}
