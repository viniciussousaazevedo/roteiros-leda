package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if (rightIndex > array.length-1 || leftIndex < 0 || leftIndex >= rightIndex || array.length == 1) {
			return;
		}
		
		int pivot = partition(array, leftIndex, rightIndex, leftIndex);
		
		sort(array, leftIndex, pivot-1);
		sort(array, pivot+1, rightIndex);
	
	}
	
	private int partition(T[] array, int leftIndex, int rightIndex, int pivot) {
		
		int partitionIndex = pivot;
		
		for (int i = leftIndex+1; i <= rightIndex; i++) {
			
			if (array[i].compareTo(array[pivot]) <= 0) {
				partitionIndex++;
				Util.swap(array, i, partitionIndex);
			}
		}
		Util.swap(array, pivot, partitionIndex);
		return partitionIndex;
	}
}
