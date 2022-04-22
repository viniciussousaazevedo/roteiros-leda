package adt.bst;

import adt.bt.BTNode;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		if (tree1.isEmpty() && tree2.isEmpty()) { return true; } 
		if (tree1.isEmpty() || tree2.isEmpty()) { return false; }
		
		if (tree1.getRoot().getData() == tree2.getRoot().getData()) {
			boolean leftEquality = checkLeftEquality(tree1.getRoot().getLeft(), tree2.getRoot().getLeft()); 
			boolean rightEquality = checkRightEquality(tree1.getRoot().getRight(), tree2.getRoot().getRight());
			
			return leftEquality && rightEquality;
		} else { return false; }
	}

	private boolean checkLeftEquality(BTNode<T> left1, BTNode<T> left2) {
		if (left1.isEmpty() && left2.isEmpty()) { return true; }
		if (left1.isEmpty() || left2.isEmpty()) { return false; }
		
		if (left1.getData() == left2.getData()) {
			
			boolean leftEquality = checkLeftEquality(left1.getLeft(), left2.getLeft());
			boolean rightEquality = checkRightEquality(left1.getRight(), left2.getRight());
			
			return leftEquality && rightEquality;
		} else { return false; }
	}

	private boolean checkRightEquality(BTNode<T> right1, BTNode<T> right2) {
		if (right1.isEmpty() && right2.isEmpty()) { return true; }
		if (right1.isEmpty() || right2.isEmpty()) { return false; }
		
		if (right1.getData().compareTo(right2.getData()) == 0) {
			
			boolean leftEquality = checkLeftEquality(right1.getLeft(), right2.getLeft());
			boolean rightEquality = checkRightEquality(right1.getRight(), right2.getRight());
			
			return leftEquality && rightEquality;
		} else { return false; }
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		if (tree1.isEmpty() && tree2.isEmpty()) { return true; } 
		if (tree1.isEmpty() || tree2.isEmpty()) { return false; }
		
		boolean leftSimilarity = checkLeftSimilarity(tree1.getRoot().getLeft(), tree2.getRoot().getLeft()); 
		boolean rightSimilarity = checkRightSimilarity(tree1.getRoot().getRight(), tree2.getRoot().getRight());
			
		return leftSimilarity && rightSimilarity;
	}

	private boolean checkLeftSimilarity(BTNode<T> left1, BTNode<T> left2) {
		if (left1.isEmpty() && left2.isEmpty()) { return true; }
		if (left1.isEmpty() || left2.isEmpty()) { return false; }
			
		boolean leftSimilarity = checkLeftSimilarity(left1.getLeft(), left2.getLeft());
		boolean rightSimilarity = checkRightSimilarity(left1.getRight(), left2.getRight());
		
		return leftSimilarity && rightSimilarity;
	}

	private boolean checkRightSimilarity(BTNode<T> right1, BTNode<T> right2) {
		if (right1.isEmpty() && right2.isEmpty()) { return true; }
		if (right1.isEmpty() || right2.isEmpty()) { return false; }
			
		boolean leftSimilarity = checkLeftSimilarity(right1.getLeft(), right2.getLeft());
		boolean rightSimilarity = checkRightSimilarity(right1.getRight(), right2.getRight());
			
		return leftSimilarity && rightSimilarity;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		if (!tree.isEmpty()) {
			return orderStatistic(k, tree.getRoot());
		}
		return null;
	}
	
	private T orderStatistic(int k, BTNode<T> node) {
		int nodeOrder = leftSize((BSTNode<T>) node.getLeft()) + 1;
		
		if (nodeOrder > k) {
			if (node.isLeaf()) { return null; }
			return orderStatistic(k, node.getLeft());
		} else if (nodeOrder < k) {
			if (node.isLeaf()) { return null; }
			return orderStatistic(k - nodeOrder, node.getRight());
		}
		return node.getData();
	}

	private int leftSize(BSTNode<T> node) {
		int result = 0;
		
		if (!node.isEmpty()) {
			result = 1 + leftSize((BSTNode<T>) node.getLeft())
					+ leftSize((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
