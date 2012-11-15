package de.htwg.sudoku.tui;

import de.htwg.sudoku.controller.IGrid;
import de.htwg.util.observer.IObservable;

public interface ISudokuController extends IObservable {

	void reset();

	void create();

	void solve();

	void undo();

	void redo();

	void copy();

	void paste();

	void setValue(int i, int j, int k);

	String getGridString();

	String getStatus();

	void reset(IGrid grid);

}
