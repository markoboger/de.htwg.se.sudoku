package de.htwg.sudoku.controller.impl;


import javax.swing.undo.AbstractUndoableEdit;

import de.htwg.sudoku.model.ICell;

public class SetValueCommand extends AbstractUndoableEdit {

	private ICell cell;
	private int redoBuffer;
	private static final long serialVersionUID = 1L;

	public SetValueCommand(ICell cell) {
		this.cell = cell;
	}

	public void undo() {
		redoBuffer = cell.getValue();
		cell.setValue(0);
	}

	public void redo() {
		cell.setValue(redoBuffer);
	}

	public boolean canUndo() {
		return true;
	}

	public boolean canRedo() {
		return true;
	}

}
