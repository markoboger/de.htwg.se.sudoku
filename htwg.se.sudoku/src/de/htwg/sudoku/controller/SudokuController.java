package de.htwg.sudoku.controller;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.undo.UndoManager;

import de.htwg.sudoku.entities.Cell;
import de.htwg.sudoku.entities.Grid;
import de.htwg.util.observer.Observable;

public class SudokuController extends Observable {
	
	private String statusLine = "Welcome to HTWG Sudoku!";
	private Grid grid;
	private UndoManager undoManager;


	
	public SudokuController(Grid grid) {
		this.grid = grid;
		this.undoManager = new UndoManager();
	}
	
	public void setValue(int row, int column, int value) {
		Cell cell = grid.getCell(row, column);
		if (cell.isUnSet()) {
			cell.setValue(value);
			undoManager.addEdit(new SetValueCommand(cell));
			statusLine = "The cell " + cell.mkString() + " was successfully set";
		} else statusLine="The cell " + cell.mkString() + " is already set";
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
	
	public void create() {
		grid.create();
		statusLine= "New Sudoku Puzzle created";
		notifyObservers();
	}

	public String getStatus() {
		return statusLine;
	}

	public String getGridString() {
		return grid.toString();
	}
	
	public void undo() {
		if (undoManager.canUndo())
			undoManager.undo();
		statusLine = "Undo";
		notifyObservers();
	}

	public void redo() {
		if (undoManager.canRedo())
			undoManager.redo();
		statusLine= "Redo";
		notifyObservers();
	}
	
	public void copy() {
		StringSelection gridString = new StringSelection(grid.toString("0"));
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
				gridString, null);
		statusLine= "Copied Sudoku";
		notifyObservers();
	}

	public void paste() {
		Transferable transferable = Toolkit.getDefaultToolkit()
				.getSystemClipboard().getContents(null);
		if (transferable != null
				&& transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			String input;
			try {
				input = (String) transferable
						.getTransferData(DataFlavor.stringFlavor);
				grid.parseStringToGrid(input);
			} catch (UnsupportedFlavorException e1) {

				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}
		statusLine= "Pasted Sudoku";
		notifyObservers();
	}

}
