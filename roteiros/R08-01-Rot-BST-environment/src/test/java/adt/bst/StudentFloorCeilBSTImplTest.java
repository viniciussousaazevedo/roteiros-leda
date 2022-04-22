package adt.bst;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTImpl;
import adt.bst.extended.FloorCeilBST;
import adt.bst.extended.FloorCeilBSTImpl;
import adt.bt.BTNode;

public class StudentFloorCeilBSTImplTest {
	
	private BST<Integer> tree;
	private FloorCeilBST manipulation;
	private Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };

	@Before
	public void setUp() {
		tree = new BSTImpl<>();
		manipulation = new FloorCeilBSTImpl();
	}
	
	@Test
	public void testFloor() {
		assertEquals(new Integer(0), manipulation.floor(array, 0));
		assertEquals(new Integer(232), manipulation.floor(array, 500));
		assertEquals(new Integer(-40), manipulation.floor(array, -40));
		assertEquals(new Integer(6), manipulation.floor(array, 6));
		assertNull(manipulation.floor(array, -150));
	}
	
	@Test
	public void testCeil() {
		assertEquals(new Integer(-34), manipulation.ceil(array, -34));
		assertNull(manipulation.ceil(array, 500));
		assertEquals(new Integer(67), manipulation.ceil(array, 24));
		assertEquals(new Integer(-40), manipulation.ceil(array, -40));
		assertEquals(new Integer(6), manipulation.ceil(array, 6));
		assertEquals(new Integer(-40), manipulation.ceil(array, -150));
	}
}
