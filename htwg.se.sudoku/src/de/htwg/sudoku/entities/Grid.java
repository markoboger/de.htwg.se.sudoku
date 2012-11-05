package de.htwg.sudoku.entities;

import java.util.BitSet;

import de.htwg.util.shuffle.ShuffleList;

public class Grid {

	private int cellsPerEdge;
	private Cell[][] cells;
	House[] rows;
	House[] columns;
	House[] blocks;
	private int gridSize;
	private int blockSize;
	private int solutionCounter;
	private int steps;
	GridCreateStrategy creator = new GridCreateFactory(this).getInstance();


	public Grid(int blocksPerEdge) throws IllegalArgumentException {
		if (blocksPerEdge <= 0 || 4 <= blocksPerEdge)
			throw new IllegalArgumentException(
					"blocksPerEdge must be 1, 2 or 3");
		this.blockSize = blocksPerEdge;
		this.cellsPerEdge = blocksPerEdge * blockSize;
		this.gridSize = cellsPerEdge * cellsPerEdge;

		// create Cell and Houses
		cells = new Cell[cellsPerEdge][cellsPerEdge];
		rows = new House[cellsPerEdge];
		columns = new House[cellsPerEdge];
		blocks = new House[cellsPerEdge];
		// initialize Houses, connect them to their cells.
		for (int index = 0; index < cellsPerEdge; index++) {
			rows[index] = new House(cellsPerEdge);
			columns[index] = new House(cellsPerEdge);
			blocks[index] = new House(cellsPerEdge);
		}

		for (int row = 0; row < cellsPerEdge; row++) {
			for (int column = 0; column < cellsPerEdge; column++) {
				cells[row][column] = new Cell(row, column);
				rows[row].cells[column] = cells[row][column];
				columns[column].cells[row] = cells[row][column];
				blocks[blockAt(row, column)].cells[cellInBlockAt(row, column)] = cells[row][column];
			}
		}
	}

	/**
	 * calculates the index that should be used to identify the block in the
	 * blocks array at coordinate (row, column).
	 */
	public int blockAt(int row, int column) {
		return column / blockSize + (blockSize * (row / blockSize));
	}

	/**
	 * calculates the index within a block to identify the cell from the blocks
	 * cell array at coordinate (row, column).
	 */
	int cellInBlockAt(int row, int column) {
		return ((row % blockSize) + ((column % blockSize) * blockSize));
	}

	public Cell getCell(int row, int column) {
		return cells[row][column];
	}

	protected int getCellsPerEdge() {
		return cellsPerEdge;
	}

	/**
	 * sets the value of cell at (row, column) to a new value
	 */
	public void setCell(int row, int column, int value) {
		cells[row][column].setValue(value);
	}

	protected int getGridSize() {
		return gridSize;
	}
	
	int getBlockSize() {
		return blockSize;
	}

	/**
	 * returns a String of the form (i.e for size = 1) +---+ | 0 | +---+
	 */
	public String toString() {
		String newLine = System.getProperty("line.separator");
		StringBuffer result = new StringBuffer(blockSeparator(blockSize)
				+ newLine);
		for (int row = 0; row < cellsPerEdge; row++) {
			result.append(rows[row].toString() + newLine);
			if ((row + 1) % blockSize == 0) {
				result.append(blockSeparator(blockSize) + newLine);
			}

		}
		return result.toString();
	}

	/**
	 * returns a string of the form +---+ (i.e. in the case of blockSize = 1)
	 */
	String blockSeparator(int blockSize) {
		StringBuffer result = new StringBuffer("+");
		for (int i = 0; i < blockSize; i++) {
			for (int j = 0; j < blockSize * 2 + 1; j++) {
				result.append("-");
			}
			result.append("+");
		}
		return result.toString();
	}

	/**
	 * solves the Sudoku with a brute force backtracking strategy.
	 * 
	 * @return true if the sudoku was solved
	 * @throws SolutionStepException
	 */
	ShuffleList permutation;
	public boolean solve() {
		solutionCounter = 0;
		steps = 0;
		permutation = new ShuffleList(cellsPerEdge);
		boolean result = solve(0, 0, 1);
		return result;
	}

	/**
	 * does not only look for one solution but for numSolution solutions.
	 * Meaningfull arguments are 1 and 2.
	 * 
	 * @param numSolutions
	 *            the number of solutions to look for.
	 * @return true if successfull.
	 * @throws SolutionStepException
	 */
	public boolean solve(int numSolutions) {
		solutionCounter = 0;
		return solve(0, 0, numSolutions);
	}

	/**
	 * the recursive algorith for solving itself. Do not call this method, use
	 * solve() to start the algorithm.
	 * 
	 * @throws SolutionStepException
	 */
	boolean solve(int row, int column, int numSolutions) {
		steps = steps + 1;
		if (steps % 1000 == 0) {
			System.out.print(".");
		}
		if (steps > 1000000)
			System.out.println("");
		if (column == cellsPerEdge) {
			column = 0;
			row++;
			if (row == cellsPerEdge) {
				solutionCounter++;
				if (numSolutions == solutionCounter)
					return true;
				else
					return false;
			}
		}
		if (getCell(row, column).isSet()) // skip filled cells
			return solve(row, column + 1, numSolutions);
		
		for (int val = 1 ; val <= cellsPerEdge; val++) {
			int index = val -1;
			if (candidates(row, column).get(permutation.get(index)+1)) {
				getCell(row, column).setValue(permutation.get(index)+1);
				if (solve(row, column + 1, numSolutions))
					return true;
			}
		}
		getCell(row, column).setValue(0); // reset on backtrack
		return false;
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
		BitSet candidates = new BitSet(cellsPerEdge + 1);
		candidates.set(1, cellsPerEdge + 1, true);
		candidates.and(rows[row].candidates());
		candidates.and(columns[column].candidates());
		candidates.and(blocks[blockAt(row, column)].candidates());
		return candidates;
	}

	public int getSteps() {
		return steps;
	}
	
	/**
	 * sets all values for the grid and its cells back to default, i.e. to start
	 * a new game.
	 */
	public void reset() {
		for (int row = 0; row < cellsPerEdge; row++) {
			for (int column = 0; column < cellsPerEdge; column++) {
				cells[row][column].setValue(0);
				cells[row][column].setGiven(false);
				cells[row][column].setShowCandidates(false);
			}
		}
	}
	
	public void create() {
		creator .createNewGrid(this);
	}

}
