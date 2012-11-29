package de.htwg.sudoku.aview.gui;

import java.awt.Color;


public final class Constances {
	private Constances() {};

	/** The color used to fill cells that are given at the start of the game. */
	public static final Color GIVEN_BACKGROUND_COLOR = new Color(200, 200, 255);

	/** The color used to fill cells that are empty at the start of the game. */
	public static final Color CELL_BACKGROUND_COLOR = new Color(224, 224, 255);

	/** The color used to fill cells that are highlighted. */
	public static final Color HIGHLIGHTED_CELL_BACKGROUND_COLOR = new Color(
			192, 255, 192);

	/** The color used to fill supporting cells. */
	public static final Color SUPPORTING_CELL_BACKGROUND_COLOR = new Color(255,
			255, 192);

	/** The color used to draw a 3-D rectangle in each cell. */
	public static final Color RECTANGLE_3D_COLOR = Color.LIGHT_GRAY;

	/** The color used to paint digits (values and candidates). */
	public static final Color DIGIT_COLOR = Color.BLACK;

	/** The color used to fill ... */
	public static final Color FILL_COLOR = Color.LIGHT_GRAY;

	/** The color used in the very background, shows between panels. */
	public static final Color GRID_BACKGROUND_COLOR = Color.BLACK;

	/** The number of pixels to be used as insets for cells' 3-D rectangles. */
	public static final int INSET = 3;

	/** Inset used for toggle buttons */
	public static final int HORIZONTAL_INSET_SIZE = 7;

}
