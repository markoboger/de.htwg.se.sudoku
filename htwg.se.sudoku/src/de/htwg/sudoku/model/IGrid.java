package de.htwg.sudoku.model;

import java.util.BitSet;

public interface IGrid {

	/**
	 * @param row
	 * @param column
	 * @return the cell at coordinates (row, col)
	 */
	ICell getICell(int row, int column);

	/**
	 * Calculate a solution for the Sudoku puzzle. 
	 * @return true if a solution was found, false if no solution was found.
	 */
	boolean solve();
	
    /**
     * @return the number of cells per edge. The typical value is 9 for a 9*9 Sudoku puzzle. Alternative values are 1, 4, 9 or 16. 
     */
    int getCellsPerEdge();
    
    /**
     * @return the number of cells per block. The typical value is 3 for a 9*9 Sudoku puzzle. Alternative values are 1, 2, 3 and 4.
     */
    int getBlockSize();
    
    /**
     * Calculates the id of the block at coordinates (row, col).
     * @param row
     * @param column
     * @return an index value between 0 and the number of blocks.
     */
    int blockAt(int row, int column);

	/**
	 * @return the number of steps that were used in the last solve run.
	 */
	int getSteps();

	/**
	 * Set the values of all cells back to initial values.
	 */
	void reset();

	/**
	 * Create a new Sudoku puzzle.
	 */
	void create();

	/**
	 * @param string - the pattern.
	 * @return a textual representation of the Sudoku puzzle following a pattern.
	 */
	String toString(String string);

	/**
	 * Fill the cells of a grid with values parsed from the input string.
	 * @param input
	 * @return true if a value for every cell was found, false if not enough values were found.
	 */
	boolean parseStringToGrid(String input);

	/**
	 * A representation of the available values for a cell at the coordinates (row, col) in a BitSet. 
	 * @param row
	 * @param col
	 * @return
	 */
	BitSet candidates(int row, int col);


}
