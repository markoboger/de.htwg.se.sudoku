package de.htwg.sudoku.entities;

import java.util.Random;

public class GridCreateSymmetricStrategy implements GridCreateStrategy {

	private Grid grid;

	public GridCreateSymmetricStrategy(Grid grid) {
		this.grid = grid;
	}

	@Override
	public void createNewGrid(Grid grid) {
		this.grid = grid;
		grid.reset();
//		createSeedGrid();
		grid.solve();
		removePairsUntilMinimalUniquelySolvable();
		for (int row = 0; row < grid.getCellsPerEdge(); row++) {
			for (int column = 0; column < grid.getCellsPerEdge(); column++)
				if (grid.getCell(row, column).isSet())
					grid.getCell(row, column).setGiven(true);
		}
	}

	/**
	 * fills a grid with some initial random values. This is used to create
	 * different Sudoku games. This method is called to seed the creation of a
	 * symmetric Sudoku.
	 */
	public void createSeedGrid() {
		int numberOfRandomCells = grid.getCellsPerEdge() * grid.getCellsPerEdge() / 4; // fill
																				// a
																				// quarter
																				// of
		// the cells by
		// random
		Random random = new Random();
		int[] seedValues = new int[numberOfRandomCells];
		for (int k = 0; k < numberOfRandomCells; k++) {
			seedValues[k] = random.nextInt(grid.getCellsPerEdge()) + 1;
		}
		int l = 0;
		while (l < numberOfRandomCells) {
			int value = seedValues[l];
			int row = random.nextInt(grid.getCellsPerEdge());
			int column = random.nextInt(grid.getCellsPerEdge());
			if (grid.candidates(row, column).get(value)) {
				grid.getCell(row, column).setValue(value);
				grid.getCell(row, column).setGiven(true);

				l++;
			}
		}
	}

	private void removePairsUntilMinimalUniquelySolvable() {
		Cell cell1, cell2;
		int value1, value2;
		do {
			cell1 = getRandomCell();
			value1 = cell1.getValue();
			cell2 = getSymmetricCell(cell1);
			value2 = cell2.getValue();
			if (cell1.isSet() && cell2.isSet()) {
				cell1.reset();
				cell2.reset();
			}
		} while (hasUniqueSolution(copyGridStructure()));
		cell1.setValue(value1);
		cell2.setValue(value2);
	}

	private Grid copyGridStructure() {
		Grid copy = new Grid(grid.getBlockSize());
		int copiedValue;
		for (int row = 0; row < grid.getCellsPerEdge(); row++)
			for (int column = 0; column < grid.getCellsPerEdge(); column++) {
				copiedValue = grid.getCell(row, column).getValue();
				copy.setCell(row, column, copiedValue);
			}
		return copy;
	}

	public Cell getRandomCell() {
		Random random = new Random();
		Cell[] setCells = getSetCells();

		return setCells[random.nextInt(setCells.length)];
	}

	Cell getSymmetricCell(Cell cell) {
		int row = cell.getRow();
		int column = cell.getColumn();
		int symmetricRow = (grid.getCellsPerEdge() - 1) - row;
		int symmetricColumn = (grid.getCellsPerEdge() - 1) - column;

		return grid.getCell(symmetricRow, symmetricColumn);
	}

	public Cell[] getSetCells() {
		int i = 0;
		Cell[] setCells = new Cell[countSetCells()];
		for (int row = 0; row < grid.getCellsPerEdge(); row++)
			for (int column = 0; column < grid.getCellsPerEdge(); column++)
				if (grid.getCell(row, column).isSet()) {
					setCells[i] = grid.getCell(row, column);
					i++;
				}
		return setCells;
	}

	public int countSetCells() {
		int count = 0;
		for (int r = 0; r < grid.getCellsPerEdge(); r++) {
			count += this.grid.rows[r].countSetCells();
		}
		return count;
	}

	public void removeRndCellPair() {
		Cell cell1 = getRandomCell();
		Cell cell2 = getSymmetricCell(cell1);
		if (cell1.isSet() && cell2.isSet()) {
			cell1.reset();
			cell2.reset();
		}
	}

	public boolean hasUniqueSolution(Grid grid) {
		return !grid.solve(2);
	}

	public boolean isSymmetric() {
		for (int column = 0; column < grid.getCellsPerEdge(); column++)
			for (int row = 0; row < grid.getCellsPerEdge(); row++)
				if (grid.getCell(row, column).isSet()
						&& !grid.getCell((grid.getCellsPerEdge() - 1) - row,
								(grid.getGridSize() - 1) - column).isSet())
					return false;
		return true;
	}

}
