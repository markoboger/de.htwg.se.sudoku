package de.htwg.sudoku;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SudokuTest {

	private Sudoku sudoku1;
	String newLine = System.getProperty("line.separator");


	@Before
	public void setUp() throws Exception {
		sudoku1 = new Sudoku(1);
	}

	@Test
	public void testToString() {
		assertEquals("+---+"+newLine+"| 0 |"+newLine+"+---+"+newLine, sudoku1.toString());
	}

}
