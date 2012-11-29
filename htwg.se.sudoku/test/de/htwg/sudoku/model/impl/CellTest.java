package de.htwg.sudoku.model.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.sudoku.model.impl.Cell;

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
	public void testIsSetOrUnSet() {
		assertFalse(cell.isSet());
		assertTrue(cell.isUnSet());
		cell.setValue(1);
		assertTrue(cell.isSet());
		assertFalse(cell.isUnSet());
		cell.setValue(0);
		assertFalse(cell.isSet());
		assertTrue(cell.isUnSet());
	}
	
	@Test
	public void testReset() {
		cell.setValue(1);
		cell.reset();
		assertEquals(0, cell.getValue());
	}
	
	@Test
	public void testIsGiven() {
		assertFalse( cell.isGiven());
	}
	
	@Test
	public void testIsShowCandidates() {
		assertFalse( cell.isShowCandidates());
	}
	

}
