package de.htwg.sudoku.model;

import de.htwg.sudoku.model.ICell;

public abstract class AbstractCell implements ICell{
	protected int value;
	protected int row;
	protected int column;
	protected boolean given;
	protected boolean showCandidates;

	

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
	
	public String toString(String zero) {
		if (value ==0) {
			return zero; 
		} else {
			return ""+value;
		}
	}

	public boolean isUnSet() {
		return !isSet();
	}
	
	public void setGiven(boolean b) {
		given = b;
	}
	public boolean isGiven() {
		return given;
	}
	/**
	 * switch to make the possible candidate values of a cell visible in a GUI
	 * per cell
	 * 
	 * @param showCandidates
	 */
	public void setShowCandidates(boolean showCandidates) {
		this.showCandidates = showCandidates;
	}
	
	public boolean isShowCandidates() {
		return showCandidates;
	}
	
	public void reset() {
		setValue(0);
		setGiven(false);
		setShowCandidates(false);
	}

	public void toggleShowCandidates() {
		showCandidates = ! showCandidates;
	}

}
