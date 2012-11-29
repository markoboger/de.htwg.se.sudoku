package de.htwg.sudoku.aview.gui;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class StatusPanel extends JPanel {

	private final JLabel statusLabel = new JLabel("");
	private static final long serialVersionUID = 1L;

	public StatusPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		add(statusLabel);
	}

	public final void setText(final String text) {
		statusLabel.setText(" " + text);
	}

	public void clear() {
		statusLabel.setText(" ");
	}
}
