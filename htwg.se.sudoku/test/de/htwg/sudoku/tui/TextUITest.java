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
	public void testHandleInput() {
		tui1.handleInputOrQuit("001");
		assertEquals(1,grid1.getCell(0, 0).getValue());
		
		assertTrue(tui1.handleInputOrQuit("q"));
	}

}
