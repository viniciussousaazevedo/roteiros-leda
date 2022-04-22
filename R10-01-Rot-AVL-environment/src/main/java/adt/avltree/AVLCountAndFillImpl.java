package adt.avltree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	@Override
	protected void rebalance(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (this.calculateBalance(node) > 1) {
				if (this.calculateBalance((BSTNode<T>) node.getLeft()) == -1) {
					Util.leftRotation((BSTNode<T>) node.getLeft());
					Util.rightRotation(node);
					this.LRcounter++;
				} else {
					Util.rightRotation(node);
					this.LLcounter++;
				}
			} else if (this.calculateBalance(node) < -1) {
				if (this.calculateBalance((BSTNode<T>) node.getRight()) == 1) {
					Util.rightRotation((BSTNode<T>) node.getRight());
					Util.leftRotation(node);
					this.RLcounter++;
				} else {
					Util.leftRotation(node);
					this.RRcounter++;
				}
			}
			
			if (this.root == node && !node.getParent().isEmpty()) {
				this.root = (BSTNode<T>) node.getParent();
			}
		}
	}
	
	@Override
	public int LLcount() { return LLcounter; }

	@Override
	public int LRcount() { return LRcounter; }

	@Override
	public int RRcount() { return RRcounter; }

	@Override
	public int RLcount() { return RLcounter; }

	@Override
	public void fillWithoutRebalance(T[] array) {
	    if (array != null) {
	        Arrays.sort(array);

	        Map<Integer, List<T>> levels = new TreeMap<>();

	        auxFillWithoutRebalance(levels, 0, array.length - 1, 0, array);

	        for (List<T> list : levels.values()) {
	            list.forEach(t -> super.insert(t));
	        }

	    }
	}

	private void auxFillWithoutRebalance(Map<Integer, List<T>> map, int leftIndex,
	                                    int rightIndex, int level, T[] array) {
 
	    if (leftIndex <= rightIndex) {

	        int middle = (leftIndex + rightIndex) / 2;

	        if (map.containsKey(level)) {
	            map.get(level).add(array[middle]);
	        }

	        else {
	            map.put(level, new ArrayList<>());
	            map.get(level).add(array[middle]);
	        }

	        auxFillWithoutRebalance(map, leftIndex, middle - 1, level + 1, array);
	        auxFillWithoutRebalance(map, middle + 1, rightIndex, level + 1, array);
	    }
	}
}
