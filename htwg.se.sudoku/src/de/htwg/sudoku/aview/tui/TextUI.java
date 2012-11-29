package de.htwg.sudoku.aview.tui;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.htwg.sudoku.controller.ISudokuController;
import de.htwg.sudoku.model.IGrid;
import de.htwg.sudoku.model.impl.Grid;
import de.htwg.util.observer.IObserver;

public class TextUI implements IObserver {

	private ISudokuController controller;
	IGrid grid;


	public TextUI(ISudokuController controller) {
		this.controller = controller;
		controller.addObserver(this);
	}

	//@Override
	public void update() {
		printTUI();
	}

	public boolean processInputLine(String line) {
		boolean continu = true;
		if (line.equalsIgnoreCase("q")) {
			continu = false;
		}
		if (line.equalsIgnoreCase("u")) {
			// Do nothing, just redraw the updated grid
			continu = true;
		}

		if (line.equalsIgnoreCase("r")) {
			controller.reset();
		}
		if (line.equalsIgnoreCase("n")) {
			controller.create();
		}
		if (line.equalsIgnoreCase("s")) {
			controller.solve();
		}
		if (line.equalsIgnoreCase("z")) {
			controller.undo();
		}
		if (line.equalsIgnoreCase("y")) {
			controller.redo();
		}
		if (line.equalsIgnoreCase("c")) {
			controller.copy();
		}
		if (line.equalsIgnoreCase("p")) {
			controller.paste();
		}
		if (line.equalsIgnoreCase(".")) {
			grid = new Grid(1);
			controller.reset(grid);
		}
		if (line.equalsIgnoreCase("+")){
			grid = new Grid(2);
			controller.reset(grid);
		}
		if (line.equalsIgnoreCase("#")){
			grid = new Grid(3);
			controller.reset(grid);
		}
		// if the command line has the form 123, set the cell (1,2) to value 3
		if (line.matches("[0-9][0-9][0-9]")) {
			int[] arg = readToArray(line);
			controller.setValue(arg[0], arg[1], arg[2]);
		}
		// if the command line has the form 12, get the candidates of cell (1,2) 
		if (line.matches("[0-9][0-9]")) {
			int[] arg = readToArray(line);
			controller.showCandidates(arg[0], arg[1]);
		} else
		// if the command line has the form 1, highlight 1 
		if (line.matches("[0-9]")) {
			int[] arg = readToArray(line);
			controller.highlight(arg[0]);
		}
		return continu;
	}

	private int[] readToArray(String line) {
		Pattern p = Pattern.compile("[0-9]");
		Matcher m = p.matcher(line);
		int[] arg = new int[line.length()];
		for (int i = 0; i < arg.length; i++) {
			m.find();
			arg[i] = Integer.parseInt(m.group());
		}
		return arg;
	}

	public void printTUI() {
		System.out.println(controller.getGridString());
		System.out.println(controller.getStatus());
		System.out
				.println("Please enter a command( q-quit, n-new, s-solve, r-reset, z-undo, y-redo, c-copy, p-paste, .,+,#-size, x-highlight, xy-show (x,y), xyz-set (x,y) to z):");
	}
}
