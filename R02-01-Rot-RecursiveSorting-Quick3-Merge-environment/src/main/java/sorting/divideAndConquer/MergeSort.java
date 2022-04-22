package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if (rightIndex > array.length-1 || leftIndex < 0 || leftIndex >= rightIndex || array.length == 1) {
			return;
		}
		
		int middleIndex = (int) (rightIndex + leftIndex) / 2;
			
		sort(array, leftIndex, middleIndex);
		sort(array, middleIndex+1, rightIndex);
			
		merge(array, leftIndex, middleIndex, rightIndex);
	}
	
	private void merge(T[] array, int leftIndex, int middleIndex, int rightIndex) {
		
		T[] auxArray = array.clone();
		
		int i = leftIndex;
		int j = middleIndex+1;
		int k = leftIndex;
		
		while (i <= middleIndex && j <= rightIndex) {
			
			if (auxArray[i].compareTo(auxArray[j]) <= 0) {
				array[k] = auxArray[i];
				i++;
			} else {
				array[k] = auxArray[j];
				j++;
			}
			k++;
		}
		
		while (i <= middleIndex) {
			array[k] = auxArray[i];
			i++; k++;
		}
		
		while (j <= rightIndex) {
			array[k] = auxArray[j];
			j++; k++;
		}
	}
}
