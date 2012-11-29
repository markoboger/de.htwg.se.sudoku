package de.htwg.sudoku.model.impl;


/**
 * Patterns: Abstract Factory
 * This is the abstract factory for the strategy to create grids
 */
public abstract class AbstractGridCreateStrategyFactory {
	private static AbstractGridCreateStrategyFactory factory = new RemovePairsGridCreateStrategyFactory();
	public static void setFactory(AbstractGridCreateStrategyFactory fact) {
		factory = fact;
	}
	public abstract GridCreateStrategyTemplate getInstance(); 
	public static AbstractGridCreateStrategyFactory getFactory() {
		return factory; 
	}

}
