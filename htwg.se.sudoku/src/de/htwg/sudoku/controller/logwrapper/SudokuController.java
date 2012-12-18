package de.htwg.sudoku.controller.logwrapper;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.htwg.sudoku.controller.ISudokuController;
import de.htwg.sudoku.model.IGrid;
import de.htwg.sudoku.model.IGridFactory;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObservable;
import de.htwg.util.observer.IObserver;

@Singleton
public class SudokuController implements IObservable, ISudokuController {

	private Logger logger = Logger
			.getLogger("de.htwg.sudoku.controller.wrappedimpl");
	private ISudokuController realController;
	private long startTime;

	@Inject
	public SudokuController(IGridFactory gridFactory) {
		realController = new de.htwg.sudoku.controller.impl.SudokuController(
				gridFactory);
	}

	private void pre() {
		logger.debug("Controller method " + getMethodName(1) + " was called.");
		startTime = System.nanoTime();
	}

	private void post() {
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		logger.debug("Controller method " + getMethodName(1) + " was finished in " + duration + " nanoSeconds.");
	}

	private static String getMethodName(final int depth) {
		final StackTraceElement[] stack = Thread.currentThread()
				.getStackTrace();
		return stack[2 + depth].getMethodName();
	}

	public void setValue(int row, int column, int value) {
		pre();
		realController.setValue(row, column, value);
		post();
	}

	public void solve() {
		pre();
		realController.solve();
		post();
	}

	public void reset() {
		pre();
		realController.reset();
		post();
	}

	public void create() {
		pre();
		realController.create();
		post();
	}

	public String getStatus() {
		return realController.getStatus();
	}

	public String getGridString() {
		pre();
		String result = realController.getGridString();
		post();
		return result;
	}

	public void undo() {
		pre();
		realController.undo();
		post();
	}

	public void redo() {
		pre();
		realController.redo();
		post();
	}

	public void copy() {
		pre();
		realController.copy();
		post();
	}

	public void paste() {
		pre();
		realController.paste();
		post();
	}

	public int getValue(int row, int column) {
		return realController.getValue(row, column);
	}

	public void showCandidates(int row, int column) {
		pre();
		realController.showCandidates(row, column);
		post();
	}

	public void highlight(int value) {
		pre();
		realController.highlight(value);
		post();
	}

	public int getCellsPerRow() {
		return realController.getCellsPerRow();
	}

	public int getBlockSize() {
		return realController.getBlockSize();
	}

	public int blockAt(int row, int column) {
		return realController.blockAt(row, column);
	}

	public void exit() {
		pre();
		System.exit(0);
		post();
	}

	public void showAllCandidates() {
		pre();
		realController.showAllCandidates();
		post();
	}

	public boolean isGiven(int row, int column) {
		return realController.isGiven(row, column);
	}

	public boolean isHighlighted(int row, int column) {
		return realController.isHighlighted(row, column);
	}

	public boolean isSet(int row, int column) {
		return realController.isSet(row, column);
	}

	public boolean isShowCandidates(int row, int column) {
		return realController.isShowCandidates(row, column);
	}

	public boolean isCandidate(int row, int column, int candidate) {
		return realController.isCandidate(row, column, candidate);
	}

	@Override
	public void parseStringToGrid(String gridString) {
		pre();
		realController.parseStringToGrid(gridString);
		post();
	}

	@Override
	public void resetSize(int newSize) {
		pre();
		realController.resetSize(newSize);
		post();
	}

	public IGrid getGrid() {
		pre();
		IGrid result = realController.getGrid();
		post();
		return result;
	}

	@Override
	public void addObserver(IObserver s) {
		pre();
		realController.addObserver(s);
		post();
	}

	@Override
	public void removeObserver(IObserver s) {
		pre();
		realController.removeObserver(s);
		post();
	}

	@Override
	public void removeAllObservers() {
		pre();
		realController.removeAllObservers();
		post();
	}

	@Override
	public void notifyObservers() {
		pre();
		realController.notifyObservers();
		post();
	}

	@Override
	public void notifyObservers(Event e) {
		pre();
		realController.notifyObservers(e);
		post();
	}

}