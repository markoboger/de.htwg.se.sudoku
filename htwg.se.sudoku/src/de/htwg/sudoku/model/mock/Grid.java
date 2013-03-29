package de.htwg.sudoku.model.mock;

import java.util.BitSet;

import de.htwg.sudoku.model.AbstractGrid;
import de.htwg.sudoku.model.ICell;
import de.htwg.sudoku.model.impl.House;

public class Grid extends AbstractGrid{

	private Cell[][] cells;
	private House[] rows;

	public Grid( int blocksPerEdge) {		
		setBlockSize(blocksPerEdge);
		setCellsPerEdge(blocksPerEdge * getBlockSize());

		rows = new House[getCellsPerEdge()];
		for (int index = 0; index < getCellsPerEdge(); index++) {
			rows[index] = new House(getCellsPerEdge());
		}

	}

	public Cell getCell(int row, int column) {
		return new Cell(row, column);
	}
	public ICell getICell(int row, int column) {
		return (ICell) getCell(row, column);
	}


	/**
	 * sets the value of cell at (row, column) to a new value
	 */
	public void setCell(int row, int column, int value) {
		cells[row][column].setValue(value);
	}

	

	/**
	 * solves the Sudoku with a brute force backtracking strategy.
	 * 
	 * @return true if the sudoku was solved
	 * @throws SolutionStepException
	 */
	public boolean solve() {
		return true;
	}

	/**
	 * calculates all values that are still valid candidates at the coordinate
	 * (row, column).
	 * 
	 * @param row
	 * @param column
	 * @return is encoded in a BitSet: if BitSet at index 1 is true, the value 1
	 *         is a valid candidate.
	 */
	public BitSet candidates(int row, int column) {
		return new BitSet(getCellsPerEdge() + 1);
	}
	
	public int getCandidate(int row, int column) {
		return 1;
	}
	

	public void reset() {
		
	}
	
	public void create() {
		
	}

	public boolean isSolved() {
		return true;
	}
	
	public boolean isSymmetric() {
		return true;
	}
	
	/**
	 * takes a String and parses numbers out of it and fills the grid with these
	 * numbers. The String should contain size*size numbers. All other
	 * characters are ignored.
	 * 
	 * @param input
	 *            must contain size*size digits.
	 * @return true if the parsing was successful, i.e. it found size*size
	 *         digits.
	 */
	public boolean parseStringToGrid(String input) {
		return true; 
	}
	
	protected House getRow(int index) {
		return rows[index];
	}

	@Override
	public int getSteps() {
		return 0;
	}

}
