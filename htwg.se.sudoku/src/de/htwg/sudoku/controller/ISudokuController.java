package de.htwg.sudoku.controller;

import de.htwg.util.observer.IObservable;

public interface ISudokuController extends IObservable {

	/**
	 * Create a string representation of the Sudoku puzzle and put it into the
	 * system clip board
	 */
	void copy();

	/**
	 * Calculate a new Sudoku puzzle.
	 */
	void create();

	/**
	 * Terminate the application.
	 */
	void exit();

	/**
	 * @return the number of cells in a row of a block. A typcial value is 3.
	 */
	int getBlockSize();

	/**
	 * @return the number of cells per row of a Sudoku puzzle. Tyical value is 9.
	 */
	int getCellsPerRow();

	/**
	 * @return the textual representation of the Sudoku puzzle.
	 */
	String getGridString();

	/**
	 * @return the textual representation of the status line.
	 */
	String getStatus();

	/**
	 * Get the value of the cell at coordinates (row, col).
	 * @param row
	 * @param column
	 * @return
	 */
	int getValue(int row, int column);

	/**
	 * All cells that have the value index as a possible value (candidate)
	 * should be highlighted in a graphical user interface.
	 * 
	 * @param index
	 */
	void highlight(int index);

	/**
	 * 
	 * @param row
	 * @param column
	 * @param candidate
	 * @return true if the value of candidate can still be set in accordance to the rules of Sudoko at the coordinates (row, col).
	 */
	boolean isCandidate(int row, int column, int candidate);

	/**
	 * @param row
	 * @param column
	 * @return true if the cell at (row, col) was part of the initial puzzle, false if the cell is unset or was set by the user.
	 */
	boolean isGiven(int row, int column);

	/**
	 * @param row
	 * @param column
	 * @return true if the cell at (row, col) should be highlighted in a gui, false if not.
	 */
	boolean isHighlighted(int row, int column);

	/**
	 * @param row
	 * @param column
	 * @return true if value of the cell at (row, col) is different from zero.
	 */
	boolean isSet(int row, int column);

	/**
	 * @param row
	 * @param column
	 * @return true if the value show candidates is set to true.
	 */
	boolean isShowCandidates(int row, int column);

	/**
	 * Take the content of the system clip board and try to parse a Sudoku
	 * puzzle out of it.
	 */
	void paste();

	/**
	 * After the call to an undo, redo can re-do the undone change to the Sudoku
	 * puzzle
	 */
	void redo();

	/**
	 * Sets the Sudoku game back to initial empty values.
	 */
	void reset();

	/**
	 * In the Sudoku puzzle, set a cell with coordinates (row, col) to the value
	 * val.
	 * 
	 * @param row
	 *            - the row or vertical coordinate within the Sudoku puzzle.
	 * @param col
	 *            - the column or horizontal coordinate within the Sudoku
	 *            puzzle.
	 * @param val
	 *            - the value to be assigned to the cell.
	 */
	void setValue(int row, int col, int val);

	void showAllCandidates();

	/**
	 * The cell at (row, col) shall show the candidate values in a graphical
	 * user interface
	 * 
	 * @param row
	 * @param col
	 */
	void showCandidates(int row, int col);

	/**
	 * Calculates a solution for a Sudoku puzzle.
	 */
	void solve();

	/**
	 * Undo the last undoable change to the puzzle.
	 */
	void undo();

	/**
	 * @param row
	 * @param column
	 * @return the id of the block at coordinates (row, column)
	 */
	int blockAt(int row, int column);

	/**
	 * Try to fill a grid with numbers read from a string.
	 * @param gridString
	 */
	void parseStringToGrid(String gridString);

	/**
	 * Create a new puzzle with a size newSize
	 * @param newSize
	 */
	void resetSize(int newSize);

}
