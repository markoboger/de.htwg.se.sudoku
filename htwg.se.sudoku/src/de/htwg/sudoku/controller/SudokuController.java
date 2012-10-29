package de.htwg.sudoku.controller;

import javax.swing.undo.UndoManager;

import de.htwg.sudoku.entities.Cell;
import de.htwg.sudoku.entities.Grid;
import de.htwg.util.observer.Observable;

public class SudokuController extends Observable {
	
	private String statusMessage = "Welcome to HTWG Sudoku!";
	private Grid grid;
	private UndoManager undoManager;
	String statusLine;

	
	public SudokuController(Grid grid) {
		this.grid = grid;
		this.undoManager = new UndoManager();
	}
	
	public void setValue(int row, int column, int value) {
		Cell cell = grid.getCell(row, column);
		if (cell.isUnSet()) {
			cell.setValue(value);
			setStatusMessage("The cell " + cell.mkString() + " was successfully set");
		} else setStatusMessage("The cell " + cell.mkString() + " is already set");
		notifyObservers();
	}
	
	public void solve() {
		boolean result;
		result = grid.solve();		
		if (result) 
			statusLine="The Sudoku was solved successfully";
		else 
			statusLine="Can not solve this Sudoku within "
					+ grid.getSteps() + " steps";
		notifyObservers();
	}
	public void reset() {
		grid.reset();
		statusLine = "Sudoku was reset";
		notifyObservers();
	}

	public String getStatus() {
		return statusMessage;
	}

	private void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getGridString() {
		return grid.toString();
	}

}
