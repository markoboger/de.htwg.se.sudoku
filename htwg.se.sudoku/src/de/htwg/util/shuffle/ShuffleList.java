package de.htwg.util.shuffle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShuffleList {
	List<Integer> list = new ArrayList<Integer>();

	public ShuffleList(int size) {
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		shuffle(list);
	}
	
	public int get(int index) {
		return list.get(index);
	}

	public void shuffle(List<Integer> a) {
		int n = a.size();
		Random random = new Random();
		random.nextInt();
		for (int i = 0; i < n; i++) {
			int change = i + random.nextInt(n - i);
			swap(a, i, change);
		}
	}

	private void swap(List<Integer> a, int i, int change) {
		int helper = a.get(i);
		a.set(i, a.get(change));
		a.set(change, helper);
	}
	
}
