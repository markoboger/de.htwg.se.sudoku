package de.htwg.sudoku.aview.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class BlockPanel extends JPanel {

	private static final long serialVersionUID = 9094365991959087973L;

	public BlockPanel(int blockSize) {
		setLayout(new GridLayout(blockSize, blockSize, 1, 1));
	}

}
