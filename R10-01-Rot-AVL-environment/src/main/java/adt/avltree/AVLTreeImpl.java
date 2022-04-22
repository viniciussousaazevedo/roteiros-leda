package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {
	
	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty()) {
				BSTNode<T> sucessor = sucessor(node.getData());
				
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
			rebalanceUp((BSTNode<T>) node.getParent());
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
		rebalanceUp(newNode);
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
	

	protected int calculateBalance(BSTNode<T> node) {
		return this.height((BSTNode<T>) node.getLeft(), -1) - this.height((BSTNode<T>) node.getRight(), -1);
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

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (this.calculateBalance(node) > 1) {
				if (this.calculateBalance((BSTNode<T>) node.getLeft()) == -1) {
					Util.leftRotation((BSTNode<T>) node.getLeft());
				}
				Util.rightRotation(node);
			} else if (this.calculateBalance(node) < -1) {
				if (this.calculateBalance((BSTNode<T>) node.getRight()) == 1) {
					Util.rightRotation((BSTNode<T>) node.getRight());
				}
				Util.leftRotation(node);
			}
			
			if (this.root == node && !node.getParent().isEmpty()) {
				this.root = (BSTNode<T>) node.getParent();
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (!node.isEmpty()) {
			rebalance(node);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}
}
