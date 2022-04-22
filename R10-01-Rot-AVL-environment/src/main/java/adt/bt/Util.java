package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		node.getRight().setParent(node.getParent());
		
		if (node == node.getParent().getRight()) {
			node.getParent().setRight(node.getRight());
		} else if (node == node.getParent().getLeft()) {
			node.getParent().setLeft(node.getRight());
		}
		
		node.setParent(node.getRight());
		node.setRight(node.getParent().getLeft());
		node.getParent().setLeft(node);
		node.getRight().setParent(node);
		return (BSTNode<T>) node.getParent();
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		node.getLeft().setParent(node.getParent());
		
		if (node == node.getParent().getRight()) {
			node.getParent().setRight(node.getLeft());
		} else if (node == node.getParent().getLeft()) {
			node.getParent().setLeft(node.getLeft());
		}
		
		node.setParent(node.getLeft());
		node.setLeft(node.getParent().getRight());
		node.getParent().setRight(node);
		node.getLeft().setParent(node);
		return (BSTNode<T>) node.getParent();
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
