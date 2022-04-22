package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if (rightIndex > array.length-1 || leftIndex < 0 || leftIndex > rightIndex) {
			return;
		}
		
		int insertionIndex;
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			
			if (i != leftIndex) {
				
				insertionIndex = i;
				while (insertionIndex-1 >= leftIndex && array[insertionIndex].compareTo(array[insertionIndex-1]) < 0) {
					
					Util.swap(array, insertionIndex, insertionIndex-1);
					insertionIndex--;
				}
			}
		}
	}
}

