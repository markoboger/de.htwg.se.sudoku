package de.htwg.sudoku.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTest {

	Cell cell;

	@Before
	public void setUp() throws Exception {
		cell = new Cell(1,2);
	}

	@Test
	public void testGetValue() {
		cell.setValue(0);
		assertEquals(0, cell.getValue());
		cell.setValue(1);
		assertEquals(1, cell.getValue());
	}

	@Test
	public void testGetRow() {
		assertEquals(1, cell.getRow());
	}

	@Test
	public void testGetColumn() {
		assertEquals(2,cell.getColumn());

	}

	@Test
	public void testIsSet() {
		assertEquals(false, cell.isSet());
		cell.setValue(1);
		assertEquals(true, cell.isSet());
		cell.setValue(0);
		assertEquals(false, cell.isSet());
	}

}
