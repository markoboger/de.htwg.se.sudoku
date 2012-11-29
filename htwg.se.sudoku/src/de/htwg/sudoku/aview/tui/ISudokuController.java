package de.htwg.sudoku.aview.tui;

import de.htwg.sudoku.model.IGrid;
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

	void showCandidates(int i, int j);

	void highlight(int i);

}
