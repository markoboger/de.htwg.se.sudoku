package de.htwg.sudoku.model;



public abstract class AbstractHouse {

	private int size;
	private int blockSize;


	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	protected int getBlockSize() {
		return blockSize;
	}
	protected void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
    protected abstract AbstractCell[] getCells();
	
	/**
	 * returns a String of the form | 1 2 3 | 4 5 6 | 7 8 9 |
	 */
	public String toString() {
		return toString(" ");
	}
	public String toString(String zero) {
		String result = "|";
		for (int index = 0; index < size; index++) {
			result+=" " + getCells()[index].toString(zero);
			if (((index + 1) % blockSize) == 0) {
				result+=" |";
			}
		}
		return result;
	}

}
