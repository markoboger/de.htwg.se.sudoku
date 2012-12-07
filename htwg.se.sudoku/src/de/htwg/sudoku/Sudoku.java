package de.htwg.sudoku;

import java.util.Scanner;

import de.htwg.sudoku.controller.impl.SudokuController;
import de.htwg.sudoku.aview.gui.SudokuFrame;
import de.htwg.sudoku.model.impl.Grid;
import de.htwg.sudoku.aview.tui.TextUI;

public class Sudoku {

	private static Scanner scanner;
	private Sudoku() {super();}
	private final static int size = 3;
	public static void main(String[] args) {
		SudokuController controller = new SudokuController(new Grid(size));
		new SudokuFrame(controller);
		TextUI tui = new TextUI(controller);
		tui.printTUI();
		controller.create();
		// continue until the user decides to quit
		boolean continu = true;
		scanner = new Scanner(System.in);
		while (continu) {
		    continu = tui.processInputLine(scanner.next());		
		}
	}

}
