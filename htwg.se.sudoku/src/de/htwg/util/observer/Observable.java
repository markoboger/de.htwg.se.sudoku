package de.htwg.util.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Observable implements IObservable {

	private List<IObserver> subscribers = new ArrayList<IObserver>(2);

	public void addObserver(IObserver s) {
		subscribers.add(s);
	}

	public void removeObserver(IObserver s) {
		subscribers.remove(s);
	}

	public void removeAllObservers() {
		subscribers.clear();
	}

	public void notifyObservers() {
		notifyObservers(null);
	}

	@Override
	public void notifyObservers(Event e) {
		for ( Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
			IObserver observer = iter.next();
			observer.update(e);
		}
	}
}
