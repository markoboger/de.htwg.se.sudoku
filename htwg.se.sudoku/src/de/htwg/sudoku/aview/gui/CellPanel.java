package de.htwg.sudoku.aview.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import de.htwg.sudoku.controller.ISudokuController;

public class CellPanel extends JPanel {

	private int row;
	private int column;
	private ISudokuController controller;
	private static final long serialVersionUID = 1L;

	public CellPanel(int row, int column, ISudokuController controller) {
		this.row = row;
		this.column = column;
		this.controller = controller;
		int cellsPerEdge = controller.getCellsPerRow();
		int blockSize = controller.getBlockSize();
		setLayout(new GridLayout(blockSize, blockSize));
		CandidatePanel[] candidate = new CandidatePanel[cellsPerEdge + 1];
		
		for (int value = 1; value <= cellsPerEdge; value++) {
			candidate[value] = new CandidatePanel(row, column, value, controller);
			add(candidate[value]);
		}
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.blue);
		final int inset = Constances.INSET;
		final double scaleFactor=0.78;
		final int correction=4;

		g.setColor(Constances.CELL_BACKGROUND_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.draw3DRect(inset, inset, getWidth() - inset * 2, getHeight() - inset * 2, true);
		g.draw3DRect(inset + 1, inset + 1, getWidth() - inset * 2 - 2, getHeight()
				- inset * 2 - 2, true);

		if (controller.isGiven(row,column)) {
			g.setColor(Constances.GIVEN_BACKGROUND_COLOR);
		}
		else if (!controller.isSet(row, column) && controller.isHighlighted(row, column)){
			g.setColor(Constances.HIGHLIGHTED_CELL_BACKGROUND_COLOR);
		} else {
			g.setColor(Constances.CELL_BACKGROUND_COLOR);
		}
		g.fillRect(inset + 2, inset + 2, getWidth() - inset * 2 - correction, getHeight() - inset
				* 2 - correction);

		g.setColor(Constances.DIGIT_COLOR);
		int fontSize = (int) Math.round(getHeight() * scaleFactor);
		g.setFont(new Font("Arial", Font.PLAIN, fontSize));

		if (controller.isSet(row,column)) {
			paintValue(g, getWidth(), getHeight(), controller.getValue(row, column));
		}
	}

	private void paintValue(Graphics g, int width, int height, int v) {
		String value = String.valueOf(v);
		Rectangle2D bounds = g.getFontMetrics().getStringBounds(value, g);
		g.drawString(value, (int) (1 + width / 2 - bounds.getWidth() / 2), 
							(int) (1 + height / 2 - bounds.getHeight() / 2 - bounds.getY() + 1));
	}

}
