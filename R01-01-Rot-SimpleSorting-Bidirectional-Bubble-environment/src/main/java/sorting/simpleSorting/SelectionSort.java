package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if (rightIndex > array.length-1 || leftIndex < 0 || leftIndex > rightIndex) {
			return;
		}
		
		
		while (rightIndex > leftIndex) {
			
			int chosenIndex = leftIndex;
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				
				if (array[i].compareTo(array[chosenIndex]) < 0) {
					chosenIndex = i;
				}
			}
			if (leftIndex != chosenIndex) {
				Util.swap(array, leftIndex, chosenIndex);
			}
			leftIndex++;
		}
	}
}
