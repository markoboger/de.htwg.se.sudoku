package de.htwg.sudoku.model.mock;

import de.htwg.sudoku.model.IGrid;
import de.htwg.sudoku.model.IGridFactory;

public class GridFactory implements IGridFactory {

    @Override
    public IGrid create(int size) {
        return new Grid(size);
    }

}
