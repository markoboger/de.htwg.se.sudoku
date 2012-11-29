package de.htwg.sudoku.aview.gui;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JToggleButton;

import de.htwg.sudoku.controller.ISudokuController;

public class HighlightButton extends JToggleButton {

	private String label;
	private static final long serialVersionUID = 1L;

	public HighlightButton(String label, final ISudokuController controller, final int value) {
		super(label);	
		this.label = label;
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				controller.highlight(value);
			}
		});
	}

	public String getLabel() {
		return label;
	}

	public Insets getInsets() {
		return new Insets(0, Constances.HORIZONTAL_INSET_SIZE, 0,
				Constances.HORIZONTAL_INSET_SIZE);
	}
}
