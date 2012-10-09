package de.htwg.sudoku.entities;

public class Grid {
	
	private int cellsPerEdge;
	private Cell[][] cells;
	House[] rows;
	House[] columns;
	House[] blocks;
	private int gridSize;
	private int blockSize;

	public Grid(int blocksPerEdge) throws IllegalArgumentException {
		if ( blocksPerEdge <= 0 || 4 <= blocksPerEdge ) throw new IllegalArgumentException("blocksPerEdge must be 1, 2 or 3");
		this.blockSize=blocksPerEdge;
		this.cellsPerEdge=blocksPerEdge*blockSize;
		this.gridSize=cellsPerEdge*cellsPerEdge;
		
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
	
	protected int getGridSize() {
		return gridSize;
	}
	
	 
}
