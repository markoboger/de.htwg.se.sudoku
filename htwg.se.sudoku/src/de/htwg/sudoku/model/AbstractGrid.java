package de.htwg.sudoku.model;

import java.util.BitSet;

public abstract class AbstractGrid implements IGrid {

	protected int cellsPerEdge;
	protected int blockSize;
	@Override
	public ICell getICell(int row, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean solve() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCellsPerEdge() {
		return cellsPerEdge;
	}

	@Override
	public int getBlockSize() {
		return blockSize;
	}

	/**
	 * calculates the index that should be used to identify the block in the
	 * blocks array at coordinate (row, column).
	 */
	@Override
	public final int blockAt(int row, int column) {
		return column / blockSize + (blockSize * (row / blockSize));
	}
	
	/**
	 * calculates the index within a block to identify the cell from the blocks
	 * cell array at coordinate (row, column).
	 */
	protected int cellInBlockAt(int row, int column) {
		return ((row % blockSize) + ((column % blockSize) * blockSize));
	}

	@Override
	public int getSteps() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean parseStringToGrid(String input) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BitSet candidates(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * returns a string of the form +---+ (i.e. in the case of blockSize = 1)
	 */
	public String blockSeparator(int blockSize) {
		StringBuffer result = new StringBuffer("+");
		for (int i = 0; i < blockSize; i++) {
			for (int j = 0; j < blockSize * 2 + 1; j++) {
				result.append("-");
			}
			result.append("+");
		}
		return result.toString();
	}

}
