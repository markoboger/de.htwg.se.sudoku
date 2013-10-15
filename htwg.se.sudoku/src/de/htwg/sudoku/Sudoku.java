package de.htwg.sudoku;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.sudoku.aview.gui.SudokuFrame;
import de.htwg.sudoku.aview.tui.TextUI;
import de.htwg.sudoku.controller.ISudokuController;

public final class Sudoku {

	private static Scanner scanner;
	private static TextUI tui;
	private ISudokuController controller;
	private static Sudoku instance = null;

	public static Sudoku getInstance() {
		if (instance == null) instance = new Sudoku();
		return instance;
	}
	
	private Sudoku() {
		// Set up logging through log4j
		PropertyConfigurator.configure("log4j.properties");

		// Set up Google Guice Dependency Injector
		Injector injector = Guice.createInjector(new SudokuModule());

		// Build up the application, resolving dependencies automatically by
		// Guice
		controller = injector.getInstance(ISudokuController.class);
		@SuppressWarnings("unused")
		SudokuFrame gui = injector.getInstance(SudokuFrame.class);
		tui = injector.getInstance(TextUI.class);
		tui.printTUI();

		// Create an initial game
		controller.create();
	}
	
	public ISudokuController getController(){
		return controller;
	}
	
	public TextUI getTUI(){
		return tui;
	}

	public static void main(String[] args) {
		
		Sudoku.getInstance();

		// continue to read user input on the tui until the user decides to quit
		boolean continu = true;
		scanner = new Scanner(System.in);
		while (continu) {
			continu = tui.processInputLine(scanner.next());
		}
	}

}
