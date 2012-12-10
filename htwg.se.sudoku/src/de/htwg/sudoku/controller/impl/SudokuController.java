package de.htwg.sudoku.controller.impl;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.BitSet;

import javax.swing.undo.UndoManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.htwg.sudoku.controller.ISudokuController;
import de.htwg.sudoku.controller.SizeChangedEvent;
import de.htwg.sudoku.model.ICell;
import de.htwg.sudoku.model.IGrid;
import de.htwg.sudoku.model.IGridFactory;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.Observable;

@Singleton
public class SudokuController extends Observable implements ISudokuController {
	
	private String statusLine = "Welcome to HTWG Sudoku!";
	private IGrid grid;
	private IGridFactory gridFactory;
	private UndoManager undoManager;
	private int highlighted=0;

	@Inject
	public SudokuController(IGridFactory gridFactory) {
		this.gridFactory=gridFactory;
		this.grid = gridFactory.create(3);
		this.undoManager = new UndoManager();
	}
	

	
	public void setValue(int row, int column, int value) {
		ICell cell = grid.getICell(row, column);
		if (cell.isUnSet()) {
			cell.setValue(value);
			undoManager.addEdit(new SetValueCommand(cell));
			statusLine = "The cell " + cell.mkString() + " was successfully set";
		} else {
			statusLine="The cell " + cell.mkString() + " is already set";
		}
		notifyObservers();
	}
	
	public void solve() {
		boolean result;
		result = grid.solve();		
		if (result) {
			statusLine="The Sudoku was solved successfully";
		} else {
			statusLine="Can not solve this Sudoku within "
					+ grid.getSteps() + " steps";
		}
		notifyObservers();
	}
	public void reset() {
		grid.reset();
		statusLine = "Sudoku was reset";
		notifyObservers();
	}
	
	public void create() {
		grid.create();
		highlighted=0;
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
		if (undoManager.canUndo()){
			undoManager.undo();
		}
		statusLine = "Undo";
		notifyObservers();
	}

	public void redo() {
		if (undoManager.canRedo()){
			undoManager.redo();
		}
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

				statusLine = "Could not read from Clipboard";
			} catch (IOException e1) {

				statusLine = "Could not read from Clipboard";
			}
		}
		statusLine= "Pasted Sudoku";
		notifyObservers();
	}

	public int getValue(int row, int column) {
		return grid.getICell(row, column).getValue();
	}

	public void showCandidates(int row, int column) {
		grid.getICell(row, column).toggleShowCandidates();
		BitSet set = grid.candidates(row,column);
		statusLine = "Candidates at ("+row+","+column+") are "+set.toString();
		notifyObservers();
	}

	public void highlight(int value) {
		highlighted=value;
		notifyObservers();
	}

	public int getCellsPerRow() {
		return grid.getCellsPerEdge();
	}

	public int getBlockSize() {
		return grid.getBlockSize();
	}

	public int blockAt(int row, int column) {
		return grid.blockAt(row, column);
	}

	public void exit() {
		System.exit(0);
	}

	public void showAllCandidates() {
		for (int row = 0; row < grid.getCellsPerEdge(); row++) {
			for (int col = 0; col < grid.getCellsPerEdge(); col++) {
				showCandidates(row, col);
			}	
		}
		notifyObservers();
	}

	public boolean isGiven(int row, int column) {
		return grid.getICell(row, column).isGiven();
	}

	public boolean isHighlighted(int row, int column) {
		return grid.candidates(row, column).get(highlighted);
	}

	public boolean isSet(int row, int column) {
		return grid.getICell(row, column).isSet();
	}

	public boolean isShowCandidates(int row, int column) {
		return grid.getICell(row, column).isShowCandidates();
	}

	public boolean isCandidate(int row, int column, int candidate) {
		return grid.candidates(row, column).get(candidate);
	}

	@Override
	public void parseStringToGrid(String gridString) {
		grid.parseStringToGrid(gridString);
		notifyObservers();
		
	}

	@Override
	public void resetSize(int newSize) {
		this.grid = gridFactory.create(newSize);
		reset();
		Event event = new SizeChangedEvent(); 
		notifyObservers(event);
	}

	public IGrid getGrid() {
		return grid;
	}
	
}