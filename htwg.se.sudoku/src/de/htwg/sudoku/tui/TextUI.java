package de.htwg.sudoku.tui;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.htwg.sudoku.controller.SudokuController;
import de.htwg.util.observer.IObserver;

public class TextUI implements IObserver {
	
	private SudokuController controller;
	Scanner scanner;
	String line = "";

	public TextUI(SudokuController controller){
		this.controller = controller;
		controller.addObserver(this);
		scanner = new Scanner (System.in);	
	}

	@Override
	public void update() {
		printTUI();
	}

	public boolean iterate() {
		return handleInputOrQuit(scanner.next());
	}

	public void printTUI() {
		System.out.println(controller.getGridString());
		System.out.println(controller.getStatus());
		System.out.println("Please enter a command( q - quit, u - update, s - solve, r - reset, n - new, 1,4,9 - set size, xy - show candidates at (x,y), xyz - set cell(x,y) to z):");
	}
	
	public boolean handleInputOrQuit(String line) {	
		boolean quit=false;
		if (line.equalsIgnoreCase("q")) {
			quit=true;
		}
		if (line.equalsIgnoreCase("u")) {
			//Do nothing, just redraw the updated grid
		}

		// if the command line has the form 123, set the cell (1,2) to value 3
		if (line.matches("[0-9][0-9][0-9]")){
			Pattern p = Pattern.compile("[0-9]");
			Matcher m = p.matcher(line);
			int[] arg = new int[3];
			for (int i = 0; i < arg.length; i++) {
				m.find();
				arg[i] = Integer.parseInt(m.group());
			} 
			controller.setValue(arg[0], arg[1], arg[2]);   
		}
		
		return quit;
	}


}
