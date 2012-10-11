package de.htwg.sudoku;

import de.htwg.sudoku.controller.SudokuController;
import de.htwg.sudoku.entities.Grid;
import de.htwg.sudoku.tui.TextUI;

public class Sudoku {

	public static void main(String[] args) {
		TextUI tui = new TextUI(new SudokuController(new Grid(3)));
		tui.controlLoop();
	}

}
