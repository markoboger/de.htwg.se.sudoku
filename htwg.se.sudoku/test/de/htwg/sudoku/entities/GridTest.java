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
	public void testGrid()  {
		Grid grid = new Grid(1);
		assertEquals(1, grid.getCellsPerEdge());
		
	}

}
