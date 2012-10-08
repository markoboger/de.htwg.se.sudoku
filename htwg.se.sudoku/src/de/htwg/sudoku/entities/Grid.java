package de.htwg.sudoku.entities;

public class Grid {
	
	private int blocksPerEdge;
	private int cellsPerEdge;

	public Grid(int blocksPerEdge) throws IllegalArgumentException {
		if ( blocksPerEdge <= 0 || 3 <= blocksPerEdge ) throw new IllegalArgumentException("blocksPerEdge must be 1, 2 or 3");
		this.blocksPerEdge=blocksPerEdge;
		this.cellsPerEdge=blocksPerEdge*blocksPerEdge;
		
	}

	protected Object getCellsPerEdge() {
		return cellsPerEdge;
	}
	
	 
}
