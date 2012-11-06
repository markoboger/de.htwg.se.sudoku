package de.htwg.sudoku.entities;

/**
 * Patterns: Abstract Factory
 * This is a concrete factory.
 * It instantiates the strategy to fill pairs of cells by random.
 */
public class RandomGridCreateStrategyFactory extends
		AbstractGridCreateStrategyFactory {

	@Override
	public GridCreateStrategyTemplate getInstance() {
		return new RandomGridCreateStrategy();
	}
}
