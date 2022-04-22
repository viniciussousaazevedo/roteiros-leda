package adt.avltree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import adt.bst.BSTNode;

public class StudentAVLTest {

	private AVLTree<Integer> avl;
	private BSTNode<Integer> NIL = new BSTNode<Integer>();

	@Before
	public void setUp() {
		avl = new AVLTreeImpl<>();
	}

	@Test
	public void testInit() {
		assertTrue(avl.isEmpty());
		assertEquals(0, avl.size());
		assertEquals(-1, avl.height());
		assertEquals(NIL, avl.getRoot());
	}

	@Test
	public void testInsert() {
		avl.insert(-10);
		assertEquals(1, avl.size());
		assertEquals(0, avl.height());
		assertArrayEquals(new Integer[] { -10 }, avl.preOrder());

		assertFalse(avl.isEmpty());
		assertEquals(new Integer(-10), avl.getRoot().getData());

		avl.insert(-15);
		assertEquals(2, avl.size());
		assertEquals(1, avl.height());
		assertArrayEquals(new Integer[] { -10, -15 }, avl.preOrder());

		avl.insert(20);
		assertEquals(3, avl.size());
		assertEquals(1, avl.height());
		assertArrayEquals(new Integer[] { -10, -15, 20 }, avl.preOrder());
	}

	@Test
	public void testRemove() {
		avl.insert(55);
		avl.insert(9);
		avl.insert(91);
		avl.insert(12);

		avl.remove(-1);
		assertEquals(4, avl.size());

		avl.remove(91);
		assertEquals(3, avl.size());
		assertArrayEquals(new Integer[] { 12, 9, 55 }, avl.preOrder());

		avl.remove(12);
		assertEquals(2, avl.size());
		assertArrayEquals(new Integer[] { 55, 9 }, avl.preOrder());

		avl.remove(9);
		avl.remove(55);
		assertEquals(NIL, avl.getRoot());
		assertTrue(avl.isEmpty());
	}
	
	@Test
	public void testInsertCaseLL() {
		avl.insert(55);
		avl.insert(54);
		avl.insert(53);
		
		assertArrayEquals(new Integer[] { 54, 53, 55 }, avl.preOrder());
		
		avl.insert(52);
		avl.insert(51);
		
		assertArrayEquals(new Integer[] { 54, 52, 51, 53, 55 }, avl.preOrder());
		
		avl.insert(50);
		
		assertArrayEquals(new Integer[] { 52, 51, 50, 54, 53, 55 }, avl.preOrder());
		
		avl.insert(49);
		
		assertArrayEquals(new Integer[] { 52, 50, 49, 51, 54, 53, 55 }, avl.preOrder());
	}
	
	@Test
	public void testRemoveCaseLL() {
		avl.insert(55);
		avl.insert(54);
		avl.insert(53);
		avl.insert(52);
		avl.insert(51);
		avl.insert(50);
		avl.insert(49);
		
		avl.remove(51);
		avl.remove(53);
		avl.remove(55);
		avl.remove(54);
		
		assertArrayEquals(new Integer[] { 50, 49, 52 }, avl.preOrder());
	}
	
	@Test
	public void testInsertCaseRR() {
		avl.insert(55);
		avl.insert(56);
		avl.insert(57);
		
		assertArrayEquals(new Integer[] { 56, 55, 57 }, avl.preOrder());
		
		avl.insert(58);
		avl.insert(59);
		
		assertArrayEquals(new Integer[] { 56, 55, 58, 57, 59 }, avl.preOrder());
	}
	
	@Test
	public void testRemoveCaseRR() {
		
		avl.insert(55);
		avl.insert(56);
		avl.insert(57);
		avl.insert(58);
		avl.insert(54);
		avl.insert(59);
		
		avl.remove(54);
		avl.remove(55);
		
		assertArrayEquals(new Integer[] { 58, 56, 57, 59 }, avl.preOrder());
	}
	
	@Test
	public void testInsertCaseLR() {
		avl.insert(55);
		avl.insert(53);
		avl.insert(54);
		
		assertArrayEquals(new Integer[] { 54, 53, 55 }, avl.preOrder());
		
		avl.insert(51);
		avl.insert(52);
		
		assertArrayEquals(new Integer[] { 54, 52, 51, 53, 55 }, avl.preOrder());
	}
	
	@Test
	public void testRemoveCaseLR() {
		avl.insert(55);
		avl.insert(50);
		avl.insert(60);
		avl.insert(52);
		avl.insert(61);
		avl.insert(51);
		
		avl.remove(50);
		avl.remove(60);
		avl.remove(61);
		
		assertArrayEquals(new Integer[] { 52, 51, 55 }, avl.preOrder());
	}
	
	@Test
	public void testInsertCaseRL() {
		avl.insert(55);
		avl.insert(60);
		avl.insert(56);
		
		assertArrayEquals(new Integer[] { 56, 55, 60 }, avl.preOrder());
		
		avl.insert(65);
		avl.insert(62);
		
		assertArrayEquals(new Integer[] { 56, 55, 62, 60, 65 }, avl.preOrder());
	}
	
	@Test
	public void testRemoveCaseRL() {
		avl.insert(55);
		avl.insert(80);
		avl.insert(40);
		avl.insert(35);
		avl.insert(70);
		
		avl.remove(40);
		avl.remove(35);
		
		assertArrayEquals(new Integer[] { 70, 55, 80 }, avl.preOrder());
	}
}
