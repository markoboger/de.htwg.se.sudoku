package de.htwg.sudoku.model.impl;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import de.htwg.sudoku.model.AbstractGrid;
import de.htwg.sudoku.model.ICell;

public class Grid extends AbstractGrid{

	
	private Cell[][] cells;
	private House[] rows;
	private House[] columns;
	private House[] blocks;

	private int solutionCounter;
	private int steps;
	private List<Integer> permutation;
	private static final int MAXSIZE = 3;
	private GridCreateStrategyTemplate createStrategy = AbstractGridCreateStrategyFactory.getFactory().getInstance();

	public Grid( int blocksPerEdge) {
		if (blocksPerEdge <= 0 || MAXSIZE < blocksPerEdge){
			throw new IllegalArgumentException(
					"blocksPerEdge must be 1, 2 or 3");
		}
		setBlockSize(blocksPerEdge);
		setCellsPerEdge(blocksPerEdge * getBlockSize());

		// create Cell and Houses
		cells = new Cell[getCellsPerEdge()][getCellsPerEdge()];
		rows = new House[getCellsPerEdge()];
		columns = new House[getCellsPerEdge()];
		blocks = new House[getCellsPerEdge()];
		// initialize Houses, connect them to their cells.
		for (int index = 0; index < getCellsPerEdge(); index++) {
			rows[index] = new House(getCellsPerEdge());
			columns[index] = new House(getCellsPerEdge());
			blocks[index] = new House(getCellsPerEdge());
		}

		for (int row = 0; row < getCellsPerEdge(); row++) {
			for (int column = 0; column < getCellsPerEdge(); column++) {
				cells[row][column] = new Cell(row, column);
				rows[row].setCell(column, cells[row][column]);
				columns[column].setCell(row, cells[row][column]);
				blocks[blockAt(row, column)].setCell(cellInBlockAt(row, column), cells[row][column]);
			}
		}
	}

	public Cell getCell(int row, int column) {
		return cells[row][column];
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
		initSolve();
		return solve(0, 0, 1);
	}

	private void initSolve() {
		solutionCounter = 0;
		steps = 0;
		permutation = new ArrayList<Integer>();
		for (int i = 0; i < getCellsPerEdge(); i++) {
			permutation.add(i);
		}
		Collections.shuffle(permutation);
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
		initSolve();
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
		int c=column;
		int r = row;
		if (c == getCellsPerEdge()) {
			c = 0;
			r++;
			if (r == getCellsPerEdge()) {
				solutionCounter++;
				return (numSolutions == solutionCounter);
			}
		}
		// skip filled cells
		if (getCell(r, c).isSet()){ 
			return solve(r, c + 1, numSolutions);
		}
		for (int index = 0 ; index < getCellsPerEdge(); index++) {
			int value = permutation.get(index)+1;
			if (candidates(r, c).get(value)) {
				getCell(r, c).setValue(value);
				if (solve(r, c + 1, numSolutions)){
					return true;
				}
			}
		}
		// reset on backtrack
		getCell(r, c).setValue(0); 
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
		BitSet candidates = new BitSet(getCellsPerEdge() + 1);
		candidates.set(1, getCellsPerEdge() + 1, true);
		candidates.and(rows[row].candidates());
		candidates.and(columns[column].candidates());
		candidates.and(blocks[blockAt(row, column)].candidates());
		return candidates;
	}
	
	public int getCandidate(int row, int column) {
		Random random = new Random();
		int maxindex = this.candidates(row, column).cardinality();
		return candidates(row, column).nextSetBit(random.nextInt(maxindex));
	}
	
	public int getSteps() {
		return steps;
	}
	
	/**
	 * sets all values for the grid and its cells back to default, i.e. to start
	 * a new game.
	 */
	public void reset() {
		for (int row = 0; row < getCellsPerEdge(); row++) {
			for (int column = 0; column < getCellsPerEdge(); column++) {
				cells[row][column].setValue(0);
				cells[row][column].setGiven(false);
				cells[row][column].setShowCandidates(false);
			}
		}
	}
	
	public void create() {
		createStrategy.createNewGrid(this);
	}

	public boolean isSolved() {
		for (int row = 0; row < getCellsPerEdge(); row++) {
			for (int column = 0; column < getCellsPerEdge(); column++) {
				if (cells[row][column].isUnSet()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isSymmetric() {
		for (int column = 0; column < getCellsPerEdge(); column++){
			for (int row = 0; row < getCellsPerEdge(); row++){
				if (getCell(row, column).isSet()
						&& !getCell((getCellsPerEdge() - 1) - row,
								(getCellsPerEdge() - 1) - column).isSet()){
					return false;
				}
			}
		}
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
		int row = 0;
		int column = 0;
		String letter;
		for (int i = 0; i < input.length(); i++) {
			letter = input.substring(i, i + 1);
			if (letter.matches("[0-9]")) {
				Cell cell = getCell(row, column);
				cell.setValue(Integer.parseInt(letter));
				if (letter.matches("[1-9]")) {
					cell.setGiven(true);
				} else {
					cell.setGiven(false);
				}
				column++;
				if (column == getCellsPerEdge()) {
					column = 0;
					row++;
				}
			}
		}
		return (row == getCellsPerEdge()); 
	}
	
	protected House getRow(int index) {
		return rows[index];
	}

}
