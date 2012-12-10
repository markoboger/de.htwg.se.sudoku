package de.htwg.sudoku;

import com.google.inject.AbstractModule;

import de.htwg.sudoku.controller.ISudokuController;
import de.htwg.sudoku.model.IGridFactory;


public class SudokuModule extends AbstractModule {
	// Set the size to 1, 2 or 3 for a Sudoku with 1, 16 or 81 cells, respectively. 
	// private static final int SIZE = 3;
	
	@Override
	protected void configure() {

		bind(IGridFactory.class).to(de.htwg.sudoku.model.impl.GridFactory.class);
		bind(ISudokuController.class).to(de.htwg.sudoku.controller.impl.SudokuController.class);
		
		
	}

}
