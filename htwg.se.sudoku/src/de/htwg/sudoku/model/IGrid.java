package de.htwg.sudoku.model;

import java.util.BitSet;

public interface IGrid {
	ICell getICell(int row, int column);

	boolean solve();
	
    int getCellsPerEdge();
    
    int getBlockSize();
    int blockAt(int row, int column);

	int getSteps();

	void reset();

	void create();

	String toString(String string);

	boolean parseStringToGrid(String input);

	BitSet candidates(int row, int col);


}
