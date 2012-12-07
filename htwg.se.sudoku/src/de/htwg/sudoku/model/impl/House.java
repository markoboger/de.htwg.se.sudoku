package de.htwg.sudoku.model.impl;

import java.util.BitSet;


public class House {

	private int size;
	private int blockSize;
	private Cell[] cells;

	public House(int size) {
		this.size = size;
		blockSize = (int) Math.sqrt(size);
		cells = new Cell[size];
	}

	public void init() {
		for (int index=0;index<size;index++) {
			cells[index]= new Cell(0,index);
		}
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

	/**
	 * returns a String of the form | 1 2 3 | 4 5 6 | 7 8 9 |
	 */
	public String toString() {
		return toString(" ");
	}
	public String toString(String zero) {
		String result = "|";
		for (int index = 0; index < size; index++) {
			result+=" " + cells[index].toString(zero);
			if (((index + 1) % blockSize) == 0) {
				result+=" |";
			}
		}
		return result;
	}
	
	/**
	 * returns the values that are not yet used in this house as set.
	 */
	public BitSet candidates() {
		BitSet candidates = new BitSet(size + 1);
		candidates.set(1, size + 1, true);
		for (int index = 0; index < size; index++) {
			candidates.set(cells[index].getValue(), false);
		}
		return candidates;
	}
	
	public int countSetCells() {
		int count = 0;
		for (int index = 0; index < size; index++) {
			if (cells[index].getValue() > 0) {
				count++;
			}
		}
		return count;
	}

	public int countUnsetCells() {
		int count = 0;
		for (int index = 0; index < size; index++) {
			if (cells[index].getValue() == 0) {
				count++;
			}
		}
		return count;
	}


}
