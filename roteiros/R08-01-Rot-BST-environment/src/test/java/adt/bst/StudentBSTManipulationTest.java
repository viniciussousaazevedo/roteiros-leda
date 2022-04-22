package adt.bst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTImpl;
import adt.bst.SimpleBSTManipulationImpl;

public class StudentBSTManipulationTest {

	private BSTImpl<Integer> tree1;
	private BSTImpl<Integer> tree2;
	private BSTImpl<Integer> tree3;
	private BSTImpl<Integer> tree4;
	private BSTImpl<Integer> tree5;
	private BSTImpl<Integer> tree6;
	private SimpleBSTManipulationImpl<Integer> manipulation;
	
	private void fillTree1() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree1.insert(i);
			tree2.insert(i);
			tree3.insert(i);
			tree4.insert(i*2);
		}
		tree3.insert(99);
		tree3.insert(471);
		tree3.insert(-500);
		tree3.insert(1);
	}
	
	private void fillTree2() {
		Integer[] array = { 12, 45, 0, 10, -5, 78, 96, 5, 22 };
		for (int i : array) {
			tree1.insert(i);
			tree2.insert(i);
			tree3.insert(i);
			tree4.insert(i*2);
		}
		tree3.insert(99);
		tree3.insert(471);
		tree3.insert(-500);
		tree3.insert(1);
	}
	
	@Before
	public void setUp() {
		tree1 = new BSTImpl<>();
		tree2 = new BSTImpl<>();
		tree3 = new BSTImpl<>();
		tree4 = new BSTImpl<>();
		tree5 = new BSTImpl<>();
		tree6 = new BSTImpl<>();
		manipulation = new SimpleBSTManipulationImpl<>();
	}
	
	@Test
	public void testOrderStatisticFillTree1() {
		fillTree1();
		// order = { -40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232 };
		assertEquals(new Integer(12), manipulation.orderStatistic(tree1, 8));
		assertEquals(new Integer(0), manipulation.orderStatistic(tree1, 3));
		assertEquals(new Integer(6), manipulation.orderStatistic(tree1, 6));
		assertEquals(new Integer(-40), manipulation.orderStatistic(tree1, 1));
		assertEquals(new Integer(232), manipulation.orderStatistic(tree1, 12));
		assertEquals(new Integer(67), manipulation.orderStatistic(tree1, 10));
		assertNull(manipulation.orderStatistic(tree1, 20));
		assertNull(manipulation.orderStatistic(tree1, -3));
	}
	
	@Test
	public void testOrderStatisticFillTree2() {
		fillTree2();
		// order = { -5, 0, 5, 10, 12, 22, 45, 78, 96 }
		assertEquals(new Integer(78), manipulation.orderStatistic(tree1, 8));
		assertEquals(new Integer(5), manipulation.orderStatistic(tree1, 3));
		assertEquals(new Integer(22), manipulation.orderStatistic(tree1, 6));
		assertEquals(new Integer(-5), manipulation.orderStatistic(tree1, 1));
		assertEquals(new Integer(96), manipulation.orderStatistic(tree1, 9));
		assertNull(manipulation.orderStatistic(tree1, 12));
		assertNull(manipulation.orderStatistic(tree1, 0));
	}
	
	@Test
	public void testEqualsTrue() {
		fillTree1();
		assertTrue(manipulation.equals(tree1, tree2));
		assertTrue(manipulation.equals(tree1, tree1));
		assertTrue(manipulation.equals(tree2, tree1));
	}
	
	@Test
	public void testEqualsFalse() {
		fillTree1();
		assertFalse(manipulation.equals(tree1, tree3));
		assertFalse(manipulation.equals(tree3, tree1));
		assertFalse(manipulation.equals(tree2, tree3));
	}
	
	@Test
	public void testEqualsWithEmptyTrees() {
		fillTree1();
		assertTrue(manipulation.equals(tree5, tree6));
		assertTrue(manipulation.equals(tree6, tree5));
		assertFalse(manipulation.equals(tree1, tree5));
		assertFalse(manipulation.equals(tree3, tree6));
		assertFalse(manipulation.equals(tree3, tree5));
	}
	
	@Test
	public void testSimilarTrue() {
		fillTree1();
		assertTrue(manipulation.isSimilar(tree1, tree2));
		assertTrue(manipulation.isSimilar(tree1, tree4));
		assertTrue(manipulation.isSimilar(tree2, tree4));
		assertTrue(manipulation.isSimilar(tree4, tree1));
		assertTrue(manipulation.isSimilar(tree4, tree4));
		
	}
	
	@Test
	public void testSimilarFalse() {
		fillTree1();
		assertFalse(manipulation.isSimilar(tree3, tree4));
		assertFalse(manipulation.isSimilar(tree1, tree3));
	}
	
	@Test
	public void testSimilarWithEmptyTrees() {
		fillTree1();
		assertTrue(manipulation.isSimilar(tree5, tree6));
		assertTrue(manipulation.isSimilar(tree6, tree5));
		assertFalse(manipulation.isSimilar(tree3, tree5));
	}
	
}
