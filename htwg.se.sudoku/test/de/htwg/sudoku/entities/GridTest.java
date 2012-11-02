package de.htwg.sudoku.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
	public void testGridSize()  {
		assertEquals(1, grid1.getGridSize());
		assertEquals(16, grid2.getGridSize());
		assertEquals(81, grid3.getGridSize());
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
		assertEquals(grid1.getCell(0, 0), grid1.rows[0].getCell(0));		
		assertEquals(grid2.getCell(0, 0), grid2.rows[0].getCell(0));
		
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
		assertEquals("+---+"+newLine+"| 0 |"+newLine+"+---+"+newLine, grid1.toString());
		grid1.setCell(0,0,1);
		assertEquals("+---+"+newLine+"| 1 |"+newLine+"+---+"+newLine, grid1.toString());

	}
	
	@Test
	public void testSolve() {
		grid1.solve();
		assertEquals("+---+"+newLine+"| 1 |"+newLine+"+---+"+newLine, grid1.toString());
		grid2.solve();
		StringBuffer solution = new StringBuffer("");
		solution.append("+-----+-----+"+newLine);
		solution.append("| 1 2 | 3 4 |"+newLine);
		solution.append("| 3 4 | 1 2 |"+newLine);
		solution.append("+-----+-----+"+newLine);
		solution.append("| 2 1 | 4 3 |"+newLine);
		solution.append("| 4 3 | 2 1 |"+newLine);
		solution.append("+-----+-----+"+newLine);
		assertEquals(solution.toString(), grid2.toString());
	}
	
	@Test
	public void testReset() {
		grid2.setCell(1, 1, 1);
		assertEquals(1, grid2.getCell(1, 1).getValue());
		grid2.reset();
		assertEquals(0, grid2.getCell(1, 1).getValue());
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
