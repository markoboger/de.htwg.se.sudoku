package de.htwg.sudoku.model.mock;

import de.htwg.sudoku.model.AbstractCell;
import de.htwg.sudoku.model.AbstractHouse;
import de.htwg.sudoku.model.impl.Cell;


public class House extends AbstractHouse{

	private Cell[] cells;
	public House(int size) {
		setSize(size);
		setBlockSize((int) Math.sqrt(size));
		cells = new Cell[size];
	}

	@Override
	protected AbstractCell[] getCells() {
		return cells;
	}

}
