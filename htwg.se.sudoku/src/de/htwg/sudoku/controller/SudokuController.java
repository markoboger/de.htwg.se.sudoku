package de.htwg.sudoku.controller;

import de.htwg.sudoku.entities.Cell;
import de.htwg.sudoku.entities.Grid;
import de.htwg.util.observer.Observable;

public class SudokuController extends Observable {
	
	private String statusMessage = "Welcome to HTWG Sudoku!";
	private Grid grid;

	
	public SudokuController(Grid grid) {
		this.grid = grid;
	}
	
	public void setValue(int row, int column, int value) {
		Cell cell = grid.getCell(row, column);
		if (cell.isUnSet()) {
			cell.setValue(value);
			setStatusMessage("The cell " + cell.mkString() + " was successfully set");
		} else setStatusMessage("The cell " + cell.mkString() + " is already set");
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
