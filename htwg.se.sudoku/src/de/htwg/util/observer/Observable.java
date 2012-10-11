package de.htwg.util.observer;

import java.util.Iterator;
import java.util.Vector;

public class Observable  {

	protected Vector<IObserver> subscribers = new Vector<IObserver>(2);

	public void addObserver(IObserver s) {
		subscribers.addElement(s);
	}

	public void removeObserver(IObserver s) {
		subscribers.removeElement(s);
	}

	public void removeAllObservers() {
		subscribers.removeAllElements();
	}

	public void notifyObservers() {
		for ( Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
			IObserver observer = iter.next();
			observer.update();
		}
	}
}
