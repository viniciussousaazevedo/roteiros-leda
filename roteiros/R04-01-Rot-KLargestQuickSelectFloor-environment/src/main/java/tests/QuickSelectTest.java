package tests;

import orderStatistic.QuickSelect;

public class QuickSelectTest {

	public static void main(String[] args) {
		
		QuickSelect<Integer> implementation = new QuickSelect<Integer>();
		
		Integer[] array = new Integer[] {};
		int k = 1;
		
		System.out.println(implementation.quickSelect(array, k));
	}
}
