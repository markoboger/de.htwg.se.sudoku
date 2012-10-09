package de.htwg.sudoku.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HouseTest {
	
	House emptyHouse;
	House smallHouse;
	House fullHouse;

	@Before
	public void setUp() {
		emptyHouse = new House(1);
		emptyHouse.setCell(0, new Cell(0,0));
		
		smallHouse = new House(2);
		smallHouse.setCell(0, new Cell(0,0));
		smallHouse.setCell(1, new Cell(0,1));
		
		fullHouse = new House(2);
		fullHouse.setCell(0, new Cell(0,0));
		fullHouse.setCell(1, new Cell(0,1));
		fullHouse.getCell(0).setValue(1);
		fullHouse.getCell(1).setValue(2);
	}
	
	@Test
	public void testGetSize() {
		assertEquals(1,emptyHouse.getSize());
		assertEquals(2,smallHouse.getSize());
	}
	
	@Test
	public void testadd() {
		assertEquals(1,emptyHouse.getSize());
		emptyHouse.setCell(0,new Cell(0,0));
		assertEquals(1,emptyHouse.getSize());
	}
	
	@Test
	public void testGetCell() {
		assertEquals(1, fullHouse.getCell(0).getValue());
		assertEquals(2, fullHouse.getCell(1).getValue());
	}


}
