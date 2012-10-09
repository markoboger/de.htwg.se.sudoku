package de.htwg.sudoku.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HouseTest {

	House house1;
	House house2;
	House house4;
	House house9;
	House fullHouse;

	@Before
	public void setUp() {
		house1 = new House(1);
		house1.init();

		house2 = new House(2);
		house2.init();

		fullHouse = new House(2);
		fullHouse.init();
		fullHouse.getCell(0).setValue(1);
		fullHouse.getCell(1).setValue(2);

		house4 = new House(4);
		house4.init();

		house9 = new House(9);
		house9.init();
	}

	@Test
	public void testGetSize() {
		assertEquals(1,house1.getSize());
		assertEquals(2,house2.getSize());
	}

	@Test
	public void testadd() {
		assertEquals(1,house1.getSize());
		house1.setCell(0,new Cell(0,0));
		assertEquals(1,house1.getSize());
	}

	@Test
	public void testGetCell() {
		assertEquals(1, fullHouse.getCell(0).getValue());
		assertEquals(2, fullHouse.getCell(1).getValue());
	}

	@Test
	public void testToString() {
		assertEquals("| 0 |",house1.toString());
		house1.cells[0].setValue(1);
		assertEquals("| 1 |",house1.toString());

		assertEquals("| 0 0 | 0 0 |",house4.toString());
		house4.cells[0].setValue(1);
		house4.cells[1].setValue(2);
		house4.cells[2].setValue(3);
		assertEquals("| 1 2 | 3 0 |",house4.toString());

		//		assertEquals("| 0 0 0 | 0 0 0 | 0 0 0 |",house9.toString());
	}


}
