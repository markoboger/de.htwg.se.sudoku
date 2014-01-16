package de.htwg.sudoku.model;

public abstract class AbstractGrid implements IGrid {

	private int cellsPerEdge;
	private int blockSize;

	@Override
	public int getCellsPerEdge() {
		return cellsPerEdge;
	}
	
	protected void setCellsPerEdge(int cellsPerEdge) {
		this.cellsPerEdge = cellsPerEdge;
	}
	

	@Override
	public int getBlockSize() {
		return blockSize;
	}
	protected void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
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
	
	/**
	 * returns a String of the form (i.e for size = 1) +---+ |   | +---+
	 */
	public String toString() {
		return toString(" ");
	}
	public String toString(String zero) {
		String newLine = System.getProperty("line.separator");
		String result = blockSeparator(blockSize) + newLine;
		for (int row = 0; row < cellsPerEdge; row++) {
			result= result + getRow(row).toString(zero) + newLine;
			if ((row + 1) % blockSize == 0) {
				result= result + blockSeparator(blockSize) + newLine;
			}

		}
		return result;
	}

	protected abstract AbstractHouse getRow(int row); 

}
