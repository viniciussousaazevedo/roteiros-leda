package adt.avltree;

import static org.junit.Assert.*;

import org.junit.Test;

import adt.bst.BSTNode;
import adt.bt.BTNode;


public class StudentAVLTreeVerifierImplTest {

	private AVLTreeVerifier<Integer> verifier;

	private AVLTreeImpl<Integer> setUpAVLTree(Integer[] elements) {
		AVLTreeImpl<Integer> avl = new AVLTreeImpl<>();
		for (int i : elements) {
			avl.insert(i);
		}
		return avl;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setUpVerifier(Integer[] elements) {
		this.verifier = new AVLTreeVerifierImpl(setUpAVLTree(elements));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setUpVerifier(AVLTreeImpl<Integer> avl) {
		this.verifier = new AVLTreeVerifierImpl(avl);
	}
	
	@Test
	public void testIsAVL() {
		setUpVerifier(new Integer[] {3, 5, 20, 27, 80});
		assertTrue(verifier.isAVLTree());
		
		setUpVerifier(new Integer[] {50, 20, 1, 40, 90, 93, 120});
		assertTrue(verifier.isAVLTree());
		
		setUpVerifier(new Integer[] {50, 1, 88, 34, 455, 20, -15, 9, 31});
		assertTrue(verifier.isAVLTree());
		
		setUpVerifier(new Integer[] {});
		assertTrue(verifier.isAVLTree());
	}
	
	@Test
	public void testIsNotBST() {
		AVLTreeImpl<Integer> avl = setUpAVLTree(new Integer[] {3, 5, 20, 27, 80});
		avl.getRoot().getLeft().setData(99);
		setUpVerifier(avl);
		assertFalse(verifier.isAVLTree());
		
		avl = setUpAVLTree(new Integer[] {50, 20, 1, 40, 90, 93, 120});
		avl.getRoot().setData(200);
		setUpVerifier(avl);
		assertFalse(verifier.isAVLTree());
		
		avl = setUpAVLTree(new Integer[] {50, 1, 88, 34, 455, 20, -15, 9, 31});
		avl.getRoot().getRight().getLeft().setData(-1);
		avl.getRoot().getLeft().getLeft().setData(120);
		setUpVerifier(avl);
		assertFalse(verifier.isAVLTree());
	}
	
	@Test
	public void testIsNotAVLTree() {
		AVLTreeImpl<Integer> avl = setUpAVLTree(new Integer[] {3, 5, 20, 27, 80, 90});
		avl.getRoot().setLeft(new BSTNode<>());
		setUpVerifier(avl);
		assertFalse(verifier.isAVLTree());
		
		avl = setUpAVLTree(new Integer[] {50, 20, 2, 40, 93, 100, 1});
		avl.getRoot().setRight(new BSTNode<>());
		setUpVerifier(avl);
		assertFalse(verifier.isAVLTree());
		
		avl = setUpAVLTree(new Integer[] {50, 20, 2, 40, 93, 100, 1});
		avl.getRoot().getLeft().setRight(new BSTNode<>());
		setUpVerifier(avl);
		assertFalse(verifier.isAVLTree());
	}
	
}
