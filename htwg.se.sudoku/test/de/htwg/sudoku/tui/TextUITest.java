package de.htwg.sudoku.tui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.sudoku.controller.SudokuController;
import de.htwg.sudoku.entities.Grid;

public class TextUITest {

	private Grid grid1;
	private SudokuController controller1;
	private TextUI tui1;

	@Before
	public void setUp() throws Exception {
		grid1 = new Grid(1);
		controller1 = new SudokuController(grid1);
		tui1 = new TextUI(controller1);
	}

	@Test
	public void testprocessInputLine() {
		tui1.processInputLine("001");
		assertEquals(1,grid1.getCell(0, 0).getValue());
		
		assertFalse(tui1.processInputLine("q"));
	}
	
	@Test
	public void testStatus() {
		assertEquals("Welcome to HTWG Sudoku!",controller1.getStatus());
		tui1.processInputLine("001");
		assertEquals("The cell (0,0) = 1 was successfully set",controller1.getStatus());
		tui1.processInputLine("r");
		assertEquals("Sudoku was reset",controller1.getStatus());
	}

}
