package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		
		for (int i = 0; i < array.length; i++) {
			this.insert(array[i]);
		}
		
		return recursiveFloor(array, numero);
	}

	private Integer recursiveFloor(Integer[] array, double numero) {
		Integer root = this.rootElement();
		Integer floor = null;
		if (root != null) {
			
			if (root == numero) {
				return root;
			} else if (root < numero) {
					floor = root;
			}
			this.extractRootElement();
			
			Integer recursiveFloor = recursiveFloor(array, numero); 
			if (recursiveFloor != null && (floor == null || recursiveFloor > floor)) {
				floor = recursiveFloor;
			}
		}
		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		this.buildHeap(array);
		return recursiveCeil(array, numero);
	}

	private Integer recursiveCeil(Integer[] array, double numero) {
		Integer root = this.rootElement();
		Integer ceil = null;
		if (root != null) {
			if (root == numero) {
				return root;
			} else if (root > numero) {
					ceil = root;
			}
			this.extractRootElement();
			
			Integer recursiveCeil = recursiveCeil(array, numero); 
			if (recursiveCeil != null && (ceil == null || recursiveCeil < ceil)) {
				ceil = recursiveCeil;
			}
		}
		return ceil;
	}

}
