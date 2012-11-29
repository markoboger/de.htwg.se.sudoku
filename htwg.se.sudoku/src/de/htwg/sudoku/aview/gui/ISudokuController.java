package de.htwg.sudoku.aview.gui;

import javax.swing.JFrame;

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

	void showCandidates(int row, int column);

	void highlight(int value);

	int getGridSize();

	int getBlockSize();

	int blockAt(int row, int column);

	void exit();

	void showAllCandidates();

	boolean isGiven(int row, int column);

	boolean isHighlighted(int row, int column);

	boolean isSet(int row, int column);

	int getValue(int row, int column);

	boolean isShowCandidates(int row, int column);

	boolean isCandidate(int row, int column, int candidate);

	void save(JFrame sudokuFrame);

	void load(JFrame sudokuFrame);

}
