package de.htwg.sudoku.controller;

import javax.swing.JFrame;

public interface ISudokuControllerGui extends ISudokuController{

	int getCellsPerEdge();

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
