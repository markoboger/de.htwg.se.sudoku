package de.htwg.sudoku.model.mock;



public class House {

	private int size;
	private int blockSize;
	private Cell[] cells;

	public House(int size) {
		this.size = size;
		blockSize = (int) Math.sqrt(size);
		cells = new Cell[size];
	}


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
