package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This bubble sort variation has two internal iterations. In the first, it
 * pushes big elements to the right, like the normal bubble sort does. Then in
 * the second, iterates the array backwards, that is, from right to left, while
 * pushing small elements to the left. This process is repeated until the array
 * is sorted.
 */
public class BidirectionalBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if (rightIndex > array.length-1 || leftIndex < 0 || leftIndex > rightIndex) {
			return;
		}
		
		while (rightIndex > leftIndex) {
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				
				if (i == rightIndex) {
					rightIndex--;
					break;
				}
				
				if (array[i].compareTo(array[i+1]) > 0) {
					Util.swap(array, i, i+1);
				}
			}
			
			for (int j = rightIndex; j >= leftIndex; j--) {
				
				if (j == leftIndex) {
					leftIndex++;
					break;
				}
				
				if (array[j].compareTo(array[j-1]) < 0) {
					Util.swap(array, j, j-1);
				}
			}
			
		}
	}
}
