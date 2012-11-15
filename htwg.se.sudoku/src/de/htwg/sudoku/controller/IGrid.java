package de.htwg.sudoku.controller;

public interface IGrid {

	ICell getICell(int row, int column);

	boolean solve();

	int getSteps();

	void reset();

	void create();

	String toString(String string);

	boolean parseStringToGrid(String input);

}
