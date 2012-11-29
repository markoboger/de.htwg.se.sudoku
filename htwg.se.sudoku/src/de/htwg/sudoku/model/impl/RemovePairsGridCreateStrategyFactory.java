package de.htwg.sudoku.model.impl;


/**
 * Patterns: Abstract Factory
 * This is a concrete factory.
 * It instantiates the strategy to solve a grid and remove pairs of cells.
 */
public class RemovePairsGridCreateStrategyFactory extends AbstractGridCreateStrategyFactory{
	
	public GridCreateStrategyTemplate getInstance() {
		return new RemovePairsGridCreateStrategy();
		
	}

}
