package de.htwg.sudoku.model.impl;

import java.util.Random;

/**
 * Patterns: Abstract Factory, TemplateMethod
 * This class is a concrete strategy.
 * It follows the template for strategies
 */

public class RemovePairsGridCreateStrategy extends GridCreateStrategyTemplate {
	
	public void prepareGrid() {
		grid.solve();
	};
	
	@Override
	public void fillSymmetrically() {
		removePairsUntilMinimalUniquelySolvable();
	}
	

	private void removePairsUntilMinimalUniquelySolvable() {
		if (getSetCells().length > 1) {
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
	}

	private Grid copyGridStructure() {
		Grid copy = new Grid(grid.getBlockSize());
		int copiedValue;
		for (int row = 0; row < grid.getCellsPerEdge(); row++){
			for (int column = 0; column < grid.getCellsPerEdge(); column++) {
				copiedValue = grid.getCell(row, column).getValue();
				copy.setCell(row, column, copiedValue);
			}
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
		for (int row = 0; row < grid.getCellsPerEdge(); row++){
			for (int column = 0; column < grid.getCellsPerEdge(); column++){
				if (grid.getCell(row, column).isSet()) {
					setCells[i] = grid.getCell(row, column);
					i++;
				}
			}
		}
		return setCells;
	}

	public int countSetCells() {
		int count = 0;
		for (int r = 0; r < grid.getCellsPerEdge(); r++) {
			count += this.grid.getRow(r).countSetCells();
		}
		return count;
	}

	public boolean hasUniqueSolution(Grid grid) {
		return !grid.solve(2);
	}



}
