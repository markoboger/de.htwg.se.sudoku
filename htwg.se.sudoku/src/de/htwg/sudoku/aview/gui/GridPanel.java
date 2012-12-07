package de.htwg.sudoku.aview.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import de.htwg.sudoku.controller.ISudokuController;

public class GridPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public GridPanel( ISudokuController controller) {

		int cellsPerEdge = controller.getCellsPerRow();
		int blockSize = controller.getBlockSize();
		setLayout(new GridLayout(blockSize, blockSize, 2, 2));
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		BlockPanel[] block = new BlockPanel[cellsPerEdge];

		for (int index = 0; index < cellsPerEdge; index++) {
			block[index] = new BlockPanel(blockSize);
			add(block[index]);
		}

		// Draw each cell.
		for (int row = 0; row < cellsPerEdge; row++) {
			for (int column = 0; column < cellsPerEdge; column++) {
				CellPanel cellPanel = new CellPanel(row, column, controller);
				block[controller.blockAt(row, column)].add(cellPanel);
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Paint the background.
		g.setColor(Constances.GRID_BACKGROUND_COLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}
}
