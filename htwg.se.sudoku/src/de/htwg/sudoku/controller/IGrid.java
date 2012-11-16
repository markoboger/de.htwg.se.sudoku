package de.htwg.sudoku.controller;

import java.util.BitSet;

public interface IGrid {

	ICell getICell(int row, int column);

	boolean solve();

	int getSteps();

	void reset();

	void create();

	String toString(String string);

	boolean parseStringToGrid(String input);

	BitSet candidates(int row, int col);

}
