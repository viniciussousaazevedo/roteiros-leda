package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		
		return quickSelectionFloor(array, 0, array.length-1, x);
		
	}
	
	private Integer quickSelectionFloor(Integer[] array, int leftIndex, int rightIndex, int x) {
		
		if (leftIndex > rightIndex) {
			return null;
		}
		
		int pivotIndex = partition(array, leftIndex, rightIndex, leftIndex);
		
		if (array[pivotIndex] > x) {
			return quickSelectionFloor(array, leftIndex, pivotIndex-1, x);
		} else if (array[pivotIndex] < x) {
			Integer actualFloor = array[pivotIndex];
			Integer nextFloor = quickSelectionFloor(array, pivotIndex+1, rightIndex, x);
			
			if (nextFloor == null || actualFloor > nextFloor) {
				return actualFloor;
			} else {
				return nextFloor;
			}
		}
		
		return array[pivotIndex];
	}
	
	private int partition(Integer[] array, int leftIndex, int rightIndex, int pivot) {
		
		int partitionIndex = pivot;
		
		for (int i = leftIndex+1; i <= rightIndex; i++) {
			
			if (array[i].compareTo(array[pivot]) <= 0) {
				partitionIndex++;
				int aux = array[i];
				array[i] = array[partitionIndex];
				array[partitionIndex] = aux;
			}
		}
		int aux = array[pivot];
		array[pivot] = array[partitionIndex];
		array[partitionIndex] = aux;
		return partitionIndex;
	}
}
