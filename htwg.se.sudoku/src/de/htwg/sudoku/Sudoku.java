package de.htwg.sudoku;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import de.htwg.sudoku.controller.impl.SudokuController;
import de.htwg.sudoku.aview.gui.SudokuFrame;
import de.htwg.sudoku.model.impl.Grid;
import de.htwg.sudoku.aview.tui.TextUI;

public final class Sudoku {

	private static Scanner scanner;
	private Sudoku() {super();}
	private static final int SIZE = 3;
	
	static Logger logger = Logger.getLogger(Sudoku.class);
	 
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		SudokuController controller = new SudokuController(new Grid(SIZE));
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
