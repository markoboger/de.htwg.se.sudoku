package de.htwg.sudoku.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GridPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public GridPanel( ISudokuController controller) {

		int gridSize = controller.getGridSize();
		int blockSize = controller.getBlockSize();
		setLayout(new GridLayout(blockSize, blockSize, 2, 2));
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		BlockPanel[] block = new BlockPanel[gridSize];

		for (int index = 0; index < gridSize; index++) {
			block[index] = new BlockPanel(blockSize);
			add(block[index]);
		}

		// Draw each cell.
		for (int row = 0; row < gridSize; row++) {
			for (int column = 0; column < gridSize; column++) {
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
