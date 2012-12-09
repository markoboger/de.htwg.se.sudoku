package de.htwg.sudoku.aview.tui;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import de.htwg.sudoku.controller.ISudokuController;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;

public class TextUI implements IObserver {
	
	private static final int DOTSIZE=1;
	private static final int PLUSSIZE=2;
	private static final int HASHSIZE=3;
	private String newLine = System.getProperty("line.separator");

	private ISudokuController controller;
	
	private Logger logger = Logger.getLogger("de.htwg.sudoku.aview.tui");

	@Inject
	public TextUI(ISudokuController controller) {
		this.controller = controller;
		controller.addObserver(this);
	}

	//@Override
	public void update(Event e) {
		printTUI();
	}

	public boolean processInputLine(String line) {
		boolean continu = true;
		if (line.equalsIgnoreCase("q")) {
			continu = false;
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
			controller.resetSize(DOTSIZE);
		}
		if (line.equalsIgnoreCase("+")){
			controller.resetSize(PLUSSIZE);
		}
		if (line.equalsIgnoreCase("#")){
			controller.resetSize(HASHSIZE);
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
		} 
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
		logger.info(newLine + controller.getGridString());
		logger.info(newLine + controller.getStatus());
		logger.info(newLine + "Possible commands: q-quit, n-new, s-solve, r-reset, z-undo, y-redo, c-copy, p-paste, .,+,#-size, x-highlight, xy-show (x,y), xyz-set (x,y) to z");
	}
}
