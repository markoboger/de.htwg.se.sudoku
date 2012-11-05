package de.htwg.util.shuffle;

import static org.junit.Assert.*;

import org.junit.Test;


public class ShuffleListTest {
	 ShuffleList list1 = new ShuffleList(1);
	 ShuffleList list2 = new ShuffleList(2);
	 ShuffleList list3 = new ShuffleList(3);


	@Test
	public void testShuffle() {
		assertEquals(0,list1.get(0));
		assertTrue(list2.get(0) == 0 || list2.get(0) == 1);
		assertTrue(list2.get(1) == 0 || list2.get(1) == 1);
		assertTrue(list3.get(0) == 0 || list3.get(0) == 1 || list3.get(0) == 2);
		assertTrue(list3.get(1) == 0 || list3.get(1) == 1 || list3.get(1) == 2);
		assertTrue(list3.get(2) == 0 || list3.get(2) == 1 || list3.get(2) == 2);

	}

}
