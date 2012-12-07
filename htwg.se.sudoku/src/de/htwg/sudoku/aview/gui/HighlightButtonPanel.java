package de.htwg.sudoku.aview.gui;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import de.htwg.sudoku.controller.ISudokuController;

public class HighlightButtonPanel extends JPanel {

	private final ButtonGroup group = new ButtonGroup();
	private static final long serialVersionUID = 1L;

	public HighlightButtonPanel(final ISudokuController controller) {

		int gridSize = controller.getCellsPerRow();
		setLayout(new FlowLayout());

        add(new JLabel("Highlight:")); 

        // Selection of this button removes all highlighting.
        JToggleButton button = new HighlightButton(" ", controller, 0);
        group.add(button);
        add(button);

        // Construct the buttons used to select values to highlight.
        for (int digit = 1;digit <= gridSize ; digit++) {
            button = new HighlightButton(String.valueOf(digit), controller, digit);
            group.add(button);
            add(button);
        }
	}
}
