package de.htwg.sudoku.aview.gui;

import java.awt.Dimension;
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

        JToggleButton button;

        // Construct the buttons used to select values to highlight.
        for (int digit = 1;digit <= gridSize ; digit++) {
        	String buttontext = String.valueOf(digit);
        	if (digit == 0) {buttontext = " ";} 
            button = new HighlightButton(buttontext, controller, digit);
            button.setPreferredSize(new Dimension(25, 25));
            group.add(button);
            add(button);
        }
	}
}
