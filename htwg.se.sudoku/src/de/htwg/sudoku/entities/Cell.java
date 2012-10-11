package de.htwg.sudoku.entities;

public class Cell {
	private int value;
	private int row;
	private int column;

	public Cell(int row, int column) {
		this.row=row;
		this.column=column;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public boolean isSet() {
		return value == 0 ? false : true;
	}
	
	/**
	 * returns a String of the form "(0,0) = 1"
	 */
	public String mkString() {
		return "(" + row + "," + column + ") = " + value;
	}

	public boolean isUnSet() {
		return !isSet();
	}

}
