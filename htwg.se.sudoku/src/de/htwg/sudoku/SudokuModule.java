package de.htwg.sudoku;

import com.google.inject.AbstractModule;

import de.htwg.sudoku.controller.ISudokuController;
import de.htwg.sudoku.model.IGridFactory;


public class SudokuModule extends AbstractModule {

	
	@Override
	protected void configure() {

		bind(IGridFactory.class).to(de.htwg.sudoku.model.impl.GridFactory.class);
		bind(ISudokuController.class).to(de.htwg.sudoku.controller.logwrapper.SudokuController.class);
		
		
	}

}
