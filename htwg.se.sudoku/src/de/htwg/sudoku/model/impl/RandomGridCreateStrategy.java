package de.htwg.sudoku.model.impl;

import java.util.Random;


/**
 * Patterns: Abstract Factory, TemplateMethod This class is a concrete strategy.
 * It follows the template for strategies
 */
public class RandomGridCreateStrategy extends GridCreateStrategyTemplate {

	@Override
	public void fillSymmetrically() {
		for (int i = 0; i < grid.getCellsPerEdge(); i++) {
			Cell cell1 = getRandomCell();
			Cell cell2 = getSymmetricCell(cell1);
			cell1.setValue(grid.getCandidate(cell1.getRow(),cell1.getColumn()));
			cell2.setValue(grid.getCandidate(cell2.getRow(),cell2.getColumn()));
		}

	}

	public Cell getRandomCell() {
		Random random = new Random();
		Cell[] unsetCells = getUnsetCells();

		return unsetCells[random.nextInt(unsetCells.length)];
	}

	Cell getSymmetricCell(Cell cell) {
		int row = cell.getRow();
		int column = cell.getColumn();
		int symmetricRow = (grid.getCellsPerEdge() - 1) - row;
		int symmetricColumn = (grid.getCellsPerEdge() - 1) - column;

		return grid.getCell(symmetricRow, symmetricColumn);
	}

	public Cell[] getUnsetCells() {
		int i = 0;
		Cell[] setCells = new Cell[countUnsetCells()];
		for (int row = 0; row < grid.getCellsPerEdge(); row++){
			for (int column = 0; column < grid.getCellsPerEdge(); column++){
				if (grid.getCell(row, column).isUnSet()) {
					setCells[i] = grid.getCell(row, column);
					i++;
				}
			}
		}
		return setCells;
	}

	public int countUnsetCells() {
		int count = 0;
		for (int r = 0; r < grid.getCellsPerEdge(); r++) {
			count += this.grid.getRow(r).countUnsetCells();
		}
		return count;
	}

}
