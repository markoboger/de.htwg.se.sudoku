package de.htwg.sudoku.controller;

import de.htwg.sudoku.model.IGrid;
import de.htwg.util.observer.IObservable;

/**
 * @author mboger
 *
 */
/**
 * @author mboger
 *
 */
public interface ISudokuController extends IObservable {

	/**
	 * Sets the Sudoku game back to initial empty values. 
	 */
	void reset();

	/**
	 * Calculate a new Sudoku puzzle. 
	 */
	void create();

	/**
	 * Calculates a solution for a Sudoku puzzle.
	 */
	void solve();

	/**
	 * Undo the last undoable change to the puzzle. 
	 */
	void undo();	

	/**
	 * After the call to an undo, redo can re-do the undone change to the Sudoku puzzle
	 */
	void redo();

	/**
	 * Create a string representation of the Sudoku puzzle and put it into the system clip board
	 */
	void copy();

	/**
	 * Take the content of the system clip board and try to parse a Sudoku puzzle out of it. 
	 */
	void paste();

	/**
	 * In the Sudoku puzzle, set a cell with coordinates (row, col) to the value val.
	 * @param row - the row or vertical coordinate within the Sudoku puzzle.
	 * @param col - the column or horizontal coordinate within the Sudoku puzzle.
	 * @param val - the value to be assigned to the cell. 
	 */
	void setValue(int row, int col, int val);

	/**
	 * @return the textual representation of the Sudoku puzzle.
	 */
	String getGridString();

	/**
	 * @return the textual representation of the status line.
	 */
	String getStatus();

	/**
	 * Point the controller to a new grid and reset it. This version of reset should be called in connection with resize of the puzzle.
	 * @param grid
	 */
	void reset(IGrid grid);

	/**
	 * The cell at (row, col) shall show the candidate values in a graphical user interface
	 * @param row
	 * @param col
	 */
	void showCandidates(int row, int col);

	/**
	 * All cells that have the value index as a possible value (candidate) should be highlighted in a graphical user interface.
	 * @param index
	 */
	void highlight(int index);

}
