package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root, -1);
	}
	
	private int height(BSTNode<T> node, int h) {
		if (!node.isEmpty()) {
			h++;
			int rightHeight = height((BSTNode<T>) node.getRight(), h);
			int leftHeight = height((BSTNode<T>) node.getLeft(), h);
			h = (rightHeight > leftHeight ? rightHeight : leftHeight);
		}
		return h;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}
	
	private BSTNode<T> search(T element, BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (element.compareTo(node.getData()) > 0) {
				return search(element, (BSTNode<T>) node.getRight());
			} else if (element.compareTo(node.getData()) < 0) {
				return search(element, (BSTNode<T>) node.getLeft());
			}
			return node;
		}
		return new BSTNode<T>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element) {
		BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>()
				.data(element)
				.left(new BSTNode<T>())
				.right(new BSTNode<T>())
				.parent(new BSTNode<T>())
				.build();
		
		if (this.isEmpty()) {
			this.root = newNode;
		} else {
			insert(newNode, this.root);
		}
	}

	private void insert(BSTNode<T> newNode, BSTNode<T> node) {
		if (newNode.getData().compareTo(node.getData()) < 0) {
			if (node.getLeft().isEmpty()) {
				newNode.setParent(node);
				node.setLeft(newNode);
			} else {
				insert(newNode, (BSTNode<T>) node.getLeft());
			}
		} else {
			if (node.getRight().isEmpty()) {
				newNode.setParent(node);
				node.setRight(newNode);
			} else {
				insert(newNode, (BSTNode<T>) node.getRight());
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (this.isEmpty()) { return null; }
		return maximum(this.root);
	}
	
	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.getRight().isEmpty()) {
			return node;
		}
		return maximum((BSTNode<T>) node.getRight());
		
	}
	
	@Override
	public BSTNode<T> minimum() {
		if (this.isEmpty()) { return null; }
		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.getLeft().isEmpty()) {
			return node;
		}
		return minimum((BSTNode<T>) node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty()) {
				return sucessorByChildren((BSTNode<T>) node.getRight());
			}
			return sucessorByParents(node);
		}
		return null;
	}
	
	private BSTNode<T> sucessor(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty()) {
				return sucessorByChildren((BSTNode<T>) node.getRight());
			}
			return sucessorByParents(node);
		}
		return null;
	}

	private BSTNode<T> sucessorByChildren(BSTNode<T> node) {
		if (!node.getLeft().isEmpty()) {
			return sucessorByChildren((BSTNode<T>) node.getLeft());
		}
		return node;
	}
	
	private BSTNode<T> sucessorByParents(BSTNode<T> node) {
		if (!(node == this.root)) {
			if (node.getParent().getData().compareTo(node.getData()) > 0) {
				return (BSTNode<T>) node.getParent();
			} else {
				return sucessorByParents((BSTNode<T>) node.getParent());
			}
		}
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty()) {
				return predecessorByChildren((BSTNode<T>) node.getLeft());
			}
			return predecessorByParents(node);
		}
		return null;
	}

	private BSTNode<T> predecessorByChildren(BSTNode<T> node) {
		if (!node.getRight().isEmpty()) {
			return predecessorByChildren((BSTNode<T>) node.getRight());
		}
		return node;
	}

	private BSTNode<T> predecessorByParents(BSTNode<T> node) {
		if (!(node == this.root)) {
			if (node.getParent().getData().compareTo(node.getData()) < 0) {
				return (BSTNode<T>) node.getParent();
			} else {
				return predecessorByParents((BSTNode<T>) node.getParent());
			}
		}
		return null;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty()) {
				BSTNode<T> sucessor = sucessor(node);
				
				swapNodesContent(node, sucessor);
				
				if (node.getRight().isEmpty()) {
					node.setParent(new BSTNode<T>());
				} else {
					connectChildAndParent(sucessor);
				}
			} else if (!node.getLeft().isEmpty()) {
				if (node == this.root) {
					this.root = (BSTNode<T>) node.getLeft();
				}
				connectChildAndParent(node);
			} else {
				node.setData(null);
			}
		}
	}

	private void connectChildAndParent(BSTNode<T> node) {
		BSTNode<T> child = (!node.getLeft().isEmpty() ? (BSTNode<T>) node.getLeft() : (BSTNode<T>) node.getRight());
		child.setParent(node.getParent());
		
		if (node == node.getParent().getLeft()) {
			node.getParent().setLeft(child);
		} else {
			node.getParent().setRight(child);
		}
	}

	private void swapNodesContent(BSTNode<T> node, BSTNode<T> sucessor) {
		T aux = node.getData();
		
		node.setData(sucessor.getData());
		sucessor.setData(aux);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		ArrayList<T> preOrderArray = new ArrayList<T>();
		if (this.isEmpty()) {
			return (T[]) preOrderArray.toArray(new Comparable[0]);
		}
		
		return preOrder(preOrderArray, this.root);
	}

	
	@SuppressWarnings("unchecked")
	private T[] preOrder(ArrayList<T> preOrderArray, BSTNode<T> node) {
		preOrderArray.add(node.getData());
		
		if (!node.getLeft().isEmpty()) {
			preOrder(preOrderArray, (BSTNode<T>) node.getLeft());
		}
		if (!node.getRight().isEmpty()) {
			preOrder(preOrderArray, (BSTNode<T>) node.getRight());
		}
		return (T[]) preOrderArray.toArray(new Comparable[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		ArrayList<T> orderArray = new ArrayList<T>();
		if (this.isEmpty()) {
			return (T[]) orderArray.toArray(new Comparable[0]);
		}
		
		return order(orderArray, this.root);
	}
	
	@SuppressWarnings("unchecked")
	private T[] order(ArrayList<T> orderArray, BSTNode<T> node) {
		if (!node.getLeft().isEmpty()) {
			order(orderArray, (BSTNode<T>) node.getLeft());
		}
		orderArray.add(node.getData());
		if (!node.getRight().isEmpty()) {
			order(orderArray, (BSTNode<T>) node.getRight());
		}
		return (T[]) orderArray.toArray(new Comparable[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		ArrayList<T> postOrderArray = new ArrayList<T>();
		if (this.isEmpty()) {
			return (T[]) postOrderArray.toArray(new Comparable[0]);
		}
		
		return postOrder(postOrderArray, this.root);
	}

	@SuppressWarnings("unchecked")
	private T[] postOrder(ArrayList<T> postOrderArray, BSTNode<T> node) {
		if (!node.getLeft().isEmpty()) {
			postOrder(postOrderArray, (BSTNode<T>) node.getLeft());
		}
		if (!node.getRight().isEmpty()) {
			postOrder(postOrderArray, (BSTNode<T>) node.getRight());
		}
		postOrderArray.add(node.getData());
		
		return (T[]) postOrderArray.toArray(new Comparable[0]);
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
