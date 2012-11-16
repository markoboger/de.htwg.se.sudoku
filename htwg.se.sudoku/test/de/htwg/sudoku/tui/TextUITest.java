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
	public void testStatus001() {
		assertEquals("Welcome to HTWG Sudoku!",controller1.getStatus());
		tui1.processInputLine("001");
		assertEquals("The cell (0,0) = 1 was successfully set",controller1.getStatus());
}
	
	@Test
	public void testStatusr() {
		tui1.processInputLine("r");
		assertEquals("Sudoku was reset",controller1.getStatus());
}
	
	@Test
	public void testStatusn() {
		tui1.processInputLine("n");
		assertEquals("New Sudoku Puzzle created",controller1.getStatus());
}
	
	@Test
	public void testStatusc() {
		tui1.processInputLine("c");
		assertEquals("Copied Sudoku", controller1.getStatus());
}
	
	@Test
	public void testStatusp() {
		tui1.processInputLine("p");
		assertEquals("Pasted Sudoku", controller1.getStatus());
}
	
	@Test
	public void testStatusz() {
		tui1.processInputLine("z");
		assertEquals("Undo", controller1.getStatus());
}
	
	@Test
	public void testStatusy() {
		tui1.processInputLine("y");
		assertEquals("Redo", controller1.getStatus());
}
	
	@Test
	public void testStatus1() {
		tui1.processInputLine("1");
		assertEquals("Sudoku was reset", controller1.getStatus());
}
	
	@Test
	public void testStatus4() {
		tui1.processInputLine("4");
		assertEquals("Sudoku was reset", controller1.getStatus());
}
	
	@Test
	public void testStatus9() {
		tui1.processInputLine("9");
		assertEquals("Sudoku was reset", controller1.getStatus());
	}

}
