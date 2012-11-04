package de.htwg.sudoku.entities;

public class GridCreateFactory {
	
	private GridCreateStrategy creator;
	
	public GridCreateFactory(Grid grid) {
		creator = new GridCreateSymmetricStrategy(grid);
	}
	
	public GridCreateStrategy getInstance() {
		return creator;
	}
	
	public void setCreator(GridCreateStrategy newInstance) {
		creator = newInstance;
	}

}
