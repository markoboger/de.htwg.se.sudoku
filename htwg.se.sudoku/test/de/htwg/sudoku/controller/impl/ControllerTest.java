package de.htwg.sudoku.controller.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.sudoku.controller.impl.SudokuController;
import de.htwg.sudoku.model.IGrid;
import de.htwg.sudoku.model.impl.Grid;

public class ControllerTest {
	String newLine = System.getProperty("line.separator");

	private IGrid grid1;
	SudokuController controller1;

	@Before
	public void setUp() throws Exception {
		grid1=new Grid(1);
		controller1 = new SudokuController(grid1);
	}

	@Test
	public void testSetCell() {
		
		controller1.setValue(0, 0, 1);
		assertEquals(1, grid1.getICell(0, 0).getValue());
		assertEquals("The cell (0,0) = 1 was successfully set", controller1.getStatus());
		controller1.setValue(0, 0, 2);
		assertEquals(1, grid1.getICell(0, 0).getValue());
		assertEquals("The cell (0,0) = 1 is already set", controller1.getStatus());	
	}
	
	@Test
	public void testGetGridString() {
		assertEquals("+---+"+newLine+"|   |"+newLine+"+---+"+newLine,controller1.getGridString());
	}
	
	@Test
	public void testStatus() {
		controller1.solve();
		assertEquals("The Sudoku was solved successfully", controller1.getStatus());
		controller1.setValue(0, 0, 1);
		controller1.copy();
		assertEquals("Copied Sudoku", controller1.getStatus());
		controller1.paste();
		assertEquals("Pasted Sudoku", controller1.getStatus());
	}
	
	@Test
	public void testUndoRedo() {
		controller1.setValue(0, 0, 1);
		controller1.undo();
		assertEquals(0, controller1.getValue(0,0));
		controller1.redo();
		assertEquals(1, controller1.getValue(0,0));
	}

}
