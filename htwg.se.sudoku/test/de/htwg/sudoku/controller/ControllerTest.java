package de.htwg.sudoku.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.sudoku.entities.Grid;

public class ControllerTest {
	String newLine = System.getProperty("line.separator");

	private Grid grid1;
	SudokuController controller1;

	@Before
	public void setUp() throws Exception {
		grid1=new Grid(1);
		controller1 = new SudokuController(grid1);
	}

	@Test
	public void testSetCell() {
		
		controller1.setValue(0, 0, 1);
		assertEquals(1, grid1.getCell(0, 0).getValue());
		assertEquals("The cell (0,0) = 1 was successfully set", controller1.getStatus());
		controller1.setValue(0, 0, 2);
		assertEquals(1, grid1.getCell(0, 0).getValue());
		assertEquals("The cell (0,0) = 1 is already set", controller1.getStatus());	
	}
	
	@Test
	public void testGetGridString() {
		assertEquals("+---+"+newLine+"| 0 |"+newLine+"+---+"+newLine,controller1.getGridString());
	}

}
