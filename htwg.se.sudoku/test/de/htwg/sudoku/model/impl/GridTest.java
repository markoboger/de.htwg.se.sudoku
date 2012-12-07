package de.htwg.sudoku.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.sudoku.model.impl.AbstractGridCreateStrategyFactory;
import de.htwg.sudoku.model.impl.Grid;
import de.htwg.sudoku.model.impl.RandomGridCreateStrategyFactory;
import de.htwg.sudoku.model.impl.RemovePairsGridCreateStrategyFactory;

public class GridTest {
	String newLine = System.getProperty("line.separator");
	private Grid grid1, grid2, grid3;


	@Before
	public void setUp()  {
		grid1 = new Grid(1);
		grid2 = new Grid(2);
		grid3 = new Grid(3);
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
		assertEquals(1, grid1.getCellsPerEdge());
		assertEquals(4, grid2.getCellsPerEdge());
		assertEquals(9, grid3.getCellsPerEdge());	
	}
	
	@Test
	public void testGetCell() {
		for (int size = 1; size < 4; size++) {
			Grid grid = new Grid(size);
			assertReachAllCells(grid);
		}
	}
	
	@Test
	public void testRows() {
		assertEquals(grid1.getCell(0, 0), grid1.getRow(0).getCell(0));		
		assertEquals(grid2.getCell(0, 0), grid2.getRow(0).getCell(0));
		
	}
	
	@Test
	public void testBlockAt() {
		assertEquals(0, grid1.blockAt(0, 0));
		
		assertEquals(0, grid2.blockAt(0, 0));
		assertEquals(0, grid2.blockAt(0, 1));
		assertEquals(1, grid2.blockAt(0, 2));
		assertEquals(1, grid2.blockAt(0, 3));
		assertEquals(0, grid2.blockAt(1, 0));
		assertEquals(0, grid2.blockAt(1, 1));
		assertEquals(1, grid2.blockAt(1, 2));
		assertEquals(1, grid2.blockAt(1, 3));
		assertEquals(2, grid2.blockAt(2, 0));
		assertEquals(2, grid2.blockAt(2, 1));
		assertEquals(3, grid2.blockAt(2, 2));
		assertEquals(3, grid2.blockAt(2, 3));
		assertEquals(2, grid2.blockAt(3, 0));
		assertEquals(2, grid2.blockAt(3, 1));
		assertEquals(3, grid2.blockAt(3, 2));
		assertEquals(3, grid2.blockAt(3, 3));
	}
	
	@Test
	public void testBlockSeparator() {
		assertEquals("+---+", grid1.blockSeparator(1));
		assertEquals("+-----+-----+", grid1.blockSeparator(2));
		assertEquals("+-------+-------+-------+", grid1.blockSeparator(3));
	}
	
	@Test
	public void testToString() {
		assertEquals("+---+"+newLine+"|   |"+newLine+"+---+"+newLine, grid1.toString());
		grid1.setCell(0,0,1);
		assertEquals("+---+"+newLine+"| 1 |"+newLine+"+---+"+newLine, grid1.toString());

	}
	
	@Test
	public void testSolve() {
		assertTrue(grid1.solve());
		assertEquals(2,grid1.getSteps());
		assertEquals("+---+"+newLine+"| 1 |"+newLine+"+---+"+newLine, grid1.toString());
		assertFalse(grid2.isSolved());
		assertTrue(grid2.solve());
		assertTrue(grid2.isSolved());
		assertTrue(grid3.solve());	
		assertTrue(grid3.isSolved());
	}
	@Test
	public void testSolveFilled() {
		grid1.setCell(0, 0, 1);
		assertTrue(grid1.solve());
		assertEquals(2,grid1.getSteps());
		grid2.setCell(0, 0, 1);
		assertFalse(grid2.isSolved());
		assertTrue(grid2.solve());
		assertTrue(grid2.isSolved());
		grid2.setCell(0, 0, 1);
		assertTrue(grid3.solve());
		assertTrue(grid3.isSolved());
	}
	
	@Test
	public void testSolve2() {
		assertFalse(grid1.solve(2));
		assertTrue(grid2.solve(2));
	}
	
	@Test
	public void testCreate() {
		grid1.create();
		assertTrue(grid1.isSolved());
		assertTrue(grid1.isSymmetric());
		grid2.create();
		assertTrue(grid2.solve());
		assertTrue(grid2.isSymmetric());
		grid3.create();
		assertTrue(grid3.solve());
		assertTrue(grid3.isSymmetric());
	}
	
	@Test
	public void testIsSymmetric(){
		assertTrue(grid1.isSymmetric());
		grid1.setCell(0, 0,1);
		assertTrue(grid1.isSymmetric());
		assertTrue(grid2.isSymmetric());
		grid2.setCell(0, 0, 1);
		assertFalse(grid2.isSymmetric());
		grid2.setCell(3, 3, 3);
		assertTrue(grid2.isSymmetric());
		assertTrue(grid3.isSymmetric());
		grid3.setCell(4, 4, 4);
		assertTrue(grid3.isSymmetric());
	}
	
	@Test
	public void testReset() {
		grid2.setCell(1, 1, 1);
		assertEquals(1, grid2.getCell(1, 1).getValue());
		grid2.reset();
		assertEquals(0, grid2.getCell(1, 1).getValue());
	}
	@Test
	public void testGetBlockSize() {
		assertEquals(1, grid1.getBlockSize());
		assertEquals(2, grid2.getBlockSize());
		assertEquals(3, grid3.getBlockSize());
	}
	@Test
	public void testRandomStrategy(){
		AbstractGridCreateStrategyFactory.setFactory(new RandomGridCreateStrategyFactory());
		grid1.create();
		grid2.create();
		grid3.create();
	}
	@Test
	public void testRemovePairStrategy(){
		AbstractGridCreateStrategyFactory.setFactory(new RemovePairsGridCreateStrategyFactory());
//		grid1.create();
		grid2.create();
		grid3.create();
	}
	
	@Test
	public void testParseStringToGrid() {
		grid1.parseStringToGrid("1");
		assertEquals(1, grid1.getCell(0, 0).getValue());
		grid2.parseStringToGrid("12342");
		assertEquals(1, grid2.getCell(0, 0).getValue());
		assertEquals(2, grid2.getCell(0, 1).getValue());
		assertEquals(3, grid2.getCell(0, 2).getValue());
		assertEquals(4, grid2.getCell(0, 3).getValue());
		assertEquals(2, grid2.getCell(1, 0).getValue());
	}

	private boolean assertReachAllCells(Grid grid) {
		int cellsPerEdge=grid.getCellsPerEdge();
		for (int row = 0; row < cellsPerEdge; row++) {
			for (int column = 0; column < cellsPerEdge; column++) {
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
