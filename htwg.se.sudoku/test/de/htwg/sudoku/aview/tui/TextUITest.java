package de.htwg.sudoku.aview.tui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import de.htwg.sudoku.controller.impl.SudokuController;
import de.htwg.sudoku.model.impl.Grid;
import de.htwg.sudoku.model.impl.GridFactory;

public class TextUITest {

	private Grid grid1;
	private SudokuController controller1;
	private TextUI tui1;
	
	static Logger logger = Logger.getLogger(TextUITest.class);

	@Before
	public void setUp() throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		
		controller1 = new SudokuController(new GridFactory());
		controller1.resetSize(1);
		grid1 = (Grid) controller1.getGrid();
		tui1 = new TextUI(controller1);
	}

	@Test
	public void testprocessInputLine001() {
		tui1.processInputLine("001");
		assertEquals(1,grid1.getCell(0, 0).getValue());
		
		assertFalse(tui1.processInputLine("q"));
	}
	@Test
	public void testprocessInputLine00() {
		tui1.processInputLine("00");
		assertEquals("Candidates at (0,0) are {1}", controller1.getStatus());
	}
	@Test
	public void testprocessInputLine1() {
		tui1.processInputLine("1");
		assertTrue(controller1.isHighlighted(0,0));
	}
	@Test
	public void testprocessInputLineS() {
		tui1.processInputLine("s");
		assertEquals(1,controller1.getValue(0, 0));
	}
	
	@Test
	public void testStatus001() {
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
		tui1.processInputLine(".");
		assertEquals("Sudoku was reset", controller1.getStatus());
}
	
	@Test
	public void testStatus4() {
		tui1.processInputLine("+");
		assertEquals("Sudoku was reset", controller1.getStatus());
}
	
	@Test
	public void testStatus9() {
		tui1.processInputLine("#");
		assertEquals("Sudoku was reset", controller1.getStatus());
	}

}
