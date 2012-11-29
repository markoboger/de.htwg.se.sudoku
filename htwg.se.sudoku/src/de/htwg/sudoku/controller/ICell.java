package de.htwg.sudoku.controller;

public interface ICell {

	boolean isUnSet();

	void setValue(int value);

	String mkString();

	int getValue();

	boolean isGiven();

	boolean isSet();

	void setShowCandidates(boolean b);

	boolean isShowCandidates();

}
