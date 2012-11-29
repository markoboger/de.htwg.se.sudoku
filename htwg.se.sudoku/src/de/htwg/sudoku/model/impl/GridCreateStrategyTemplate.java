package de.htwg.sudoku.model.impl;


/**
 * Patterns: Strategy and TemplateMethod
 * This class is the abstract strategy for the strategy pattern.
 * At the same time it is a template for the implementation of a strategy
 */
public abstract class GridCreateStrategyTemplate {
	
	protected Grid grid;

	public void createNewGrid(Grid grid) {
		this.grid=grid;
		grid.reset();
		prepareGrid();
		fillSymmetrically();
		postProcessGrid();
	}

	public void prepareGrid() {
		//by default do nothing
	};
	public abstract void fillSymmetrically();

	private void postProcessGrid() {
		for (int row = 0; row < grid.getCellsPerEdge(); row++) {
			for (int column = 0; column < grid.getCellsPerEdge(); column++){
				if (grid.getCell(row, column).isSet()){
					grid.getCell(row, column).setGiven(true);
				}
			}
		}
		
	}
}
