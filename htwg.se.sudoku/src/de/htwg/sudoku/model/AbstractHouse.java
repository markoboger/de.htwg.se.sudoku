package de.htwg.sudoku.model;



public abstract class AbstractHouse {

	protected int size;
	protected int blockSize;
	protected AbstractCell[] cells;

	/**
	 * returns a String of the form | 1 2 3 | 4 5 6 | 7 8 9 |
	 */
	public String toString() {
		return toString(" ");
	}
	public String toString(String zero) {
		String result = "|";
		for (int index = 0; index < size; index++) {
			result+=" " + cells[index].toString(zero);
			if (((index + 1) % blockSize) == 0) {
				result+=" |";
			}
		}
		return result;
	}

}
