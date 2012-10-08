package de.htwg.sudoku.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GridTest {

	@Before
	public void setUp()  {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGridArgumentTooSmall() {
		new Grid(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGridArgumentTooLarge() {
		new Grid(4);
	}
	
	@Test
	public void testCellsPerEdge()  {
		assertEquals(1, new Grid(1).getCellsPerEdge());
		assertEquals(4, new Grid(2).getCellsPerEdge());
		assertEquals(9, new Grid(3).getCellsPerEdge());	
	}
	
	@Test
	public void testGridSize()  {
		assertEquals(1, new Grid(1).getGridSize());
		assertEquals(16, new Grid(2).getGridSize());
		assertEquals(81, new Grid(3).getGridSize());
	}
	
	@Test
	public void testGetCell() {
		for (int size = 1; size < 4; size++) {
			Grid grid = new Grid(size);
			assertReachAllCells(grid);
		}
	}

	private boolean assertReachAllCells(Grid grid) {
		int gridSize=grid.getGridSize();
		for (int row = 0; row < gridSize; row++) {
			for (int column = 0; column < gridSize; column++) {
				assertCellIsAt(row,column,grid);
			}
		}
		return true;
	}


	public boolean assertCellIsAt( int row, int column, Grid grid){
		assertEquals(row,grid.getCell(row, column).getRow());
		assertEquals(column,grid.getCell(row, column).getColumn());
		return true;
		
	}
}
