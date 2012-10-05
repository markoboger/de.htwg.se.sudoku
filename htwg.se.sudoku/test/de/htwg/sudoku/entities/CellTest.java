package de.htwg.sudoku.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTest {
	
	Cell cell;

	@Before
	public void setUp() throws Exception {
		cell = new Cell(0,0);
	}

	@Test
	public void testGetValue() {
		cell.setValue(0);
		assertEquals(0, cell.getValue());
		cell.setValue(1);
		assertEquals(1, cell.getValue());
	}

}
