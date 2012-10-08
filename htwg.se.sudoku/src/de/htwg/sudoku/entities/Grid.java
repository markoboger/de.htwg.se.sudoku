package de.htwg.sudoku.entities;

import java.util.List;
import java.util.Vector;

public class Grid {
	
	private int blocksPerEdge;
	private int cellsPerEdge;
	private Cell[][] cells;
	private House[] rows;
	private House[] columns;
	private House[] blocks;
	private int gridSize;

	public Grid(int blocksPerEdge) throws IllegalArgumentException {
		if ( blocksPerEdge <= 0 || 4 <= blocksPerEdge ) throw new IllegalArgumentException("blocksPerEdge must be 1, 2 or 3");
		this.blocksPerEdge=blocksPerEdge;
		this.cellsPerEdge=blocksPerEdge*blocksPerEdge;
		this.gridSize=cellsPerEdge*cellsPerEdge;
		
		// create and init cells 
		cells = new Cell[gridSize][gridSize];	
		for (int row = 0; row < gridSize; row++) {
			for (int column = 0; column < gridSize; column++) {
				cells[row][column] = new Cell(row, column);
			}
		}
		// create and initialize Houses, connect them to their cells.
		rows = new House[gridSize];
		columns = new House[gridSize];
		blocks = new House[gridSize];
		List<Cell> list = new Vector<Cell>();
		for (int row = 0; row < this.cellsPerEdge; row++) {
			for (int column = 0; column < this.cellsPerEdge; column++) {
				list.add(getCell(row,column));
			}
			rows[row] = (new House(list));
		}
		
		
	}
	
	public Cell getCell(int row, int column) {
		return cells[row][column];
	}

	protected Object getCellsPerEdge() {
		return cellsPerEdge;
	}
	
	protected int getGridSize() {
		return gridSize;
	}
	
	 
}
