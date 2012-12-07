package de.htwg.sudoku.aview.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import de.htwg.sudoku.controller.ISudokuController;

public class CandidatePanel extends JPanel {

	private int candidate;
	private ISudokuController controller;
	private int row;
	private int column;
	private static final float SCALE_FACTOR = 0.75f;
	private static final long serialVersionUID = 1L;

	public CandidatePanel(final int row, final int column, int candidate,
			final ISudokuController controller) {
		this.row = row;
		this.column = column;
		this.controller = controller;
		this.candidate = candidate;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int value = ((CandidatePanel) e.getSource()).getCandidate();
				if (e.getClickCount() > 1) {
					controller.setValue(row, column, value);
				} else {
					controller.showCandidates(row, column);
				}
			}
		});
	}

	public void paintComponent(Graphics g) {
		int fontSize = Math.round(SCALE_FACTOR * getHeight());
		g.setFont(new Font("Arial", Font.PLAIN, fontSize));
		if (!controller.isSet(row, column)
				&& controller.isShowCandidates(row, column)
				&& controller.isCandidate(row, column,candidate)) {
			String s = Integer.toString(candidate);
			g.drawString(s, centerX(g, s), centerY(g, s));
		}
	}

	private int centerX(Graphics g, String s) {
		Rectangle2D bounds = g.getFontMetrics().getStringBounds(s, g);
		return ((getWidth() / 2) - ((int) bounds.getWidth() / 2) + 1);
	}

	private int centerY(Graphics g, String s) {
		Rectangle2D bounds = g.getFontMetrics().getStringBounds(s, g);
		return ((getHeight() / 2) + ((int) bounds.getHeight() / 2) - 1);
	}

	public int getCandidate() {
		return candidate;
	}

}
