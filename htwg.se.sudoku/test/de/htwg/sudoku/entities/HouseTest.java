package de.htwg.sudoku.entities;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

public class HouseTest {
	
	House emptyHouse;
	House smallHouse;
	House fullHouse;

	@Before
	public void setUp() {
		List<Cell> cells0 = new Vector<Cell>();
		emptyHouse = new House(cells0);
		
		List<Cell> cells1 = new Vector<Cell>();
		cells1.add(new Cell(0,0));
		smallHouse = new House(cells1);
		
		List<Cell> cells2 = new Vector<Cell>();
		Cell cell=new Cell(0,0);
		cells2.add(cell);
		fullHouse = new House(cells2);
		cell.setValue(1);
		
		
	}
	
	@Test
	public void testGetSize() {
		assertEquals(0,emptyHouse.getSize());
		assertEquals(1,smallHouse.getSize());
	}


}
