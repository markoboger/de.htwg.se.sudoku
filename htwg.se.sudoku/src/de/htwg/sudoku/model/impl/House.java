package de.htwg.sudoku.model.impl;

import java.util.BitSet;

import de.htwg.sudoku.model.AbstractCell;
import de.htwg.sudoku.model.AbstractHouse;
import de.htwg.sudoku.model.ICell;


public class House extends AbstractHouse{
	
	private Cell[] cells;
	
	public House(int size) {
		setSize(size);
		setBlockSize((int) Math.sqrt(size));
		cells = new Cell[size];
		init();
	}

	protected final void init() {
		for (int index=0;index<getSize();index++) {
			getCells()[index]= new Cell(0,index);
		}
	}

	public ICell getCell(int index) {
		return getCells()[index];
	}

	public void setCell(int index, Cell cell){
		getCells()[index]=cell;
	}
	
	/**
	 * returns the values that are not yet used in this house as set.
	 */
	public BitSet candidates() {
		BitSet candidates = new BitSet(getSize() + 1);
		candidates.set(1, getSize() + 1, true);
		for (int index = 0; index < getSize(); index++) {
			candidates.set(getCells()[index].getValue(), false);
		}
		return candidates;
	}
	
	public int countSetCells() {
		int count = 0;
		for (int index = 0; index < getSize(); index++) {
			if (getCells()[index].getValue() > 0) {
				count++;
			}
		}
		return count;
	}

	public int countUnsetCells() {
		int count = 0;
		for (int index = 0; index < getSize(); index++) {
			if (getCells()[index].getValue() == 0) {
				count++;
			}
		}
		return count;
	}

	@Override
	protected AbstractCell[] getCells() {		
		return cells;
	}



}
