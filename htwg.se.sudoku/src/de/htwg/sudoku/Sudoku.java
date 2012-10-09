package de.htwg.sudoku;

import de.htwg.sudoku.entities.Grid;

public class Sudoku {
	Grid grid;

	public Sudoku(int blocks){
		grid = new Grid(blocks);
	}

	public String toString() {
		return grid.toString();
	}

	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku(3);
		System.out.println(sudoku.toString());
	}

}
