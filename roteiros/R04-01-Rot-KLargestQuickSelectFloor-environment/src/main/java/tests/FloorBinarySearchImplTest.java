package tests;

import orderStatistic.QuickSelect;
import problems.FloorBinarySearchImpl;

public class FloorBinarySearchImplTest {

	public static void main(String[] args) {
		
		FloorBinarySearchImpl implementation = new FloorBinarySearchImpl();
		
		Integer[] array = new Integer[] {4, 0, 9, 3, 1, 12, 20};
		int x = 30;
		
		System.out.println(implementation.floor(array, x));
	}
	
}
