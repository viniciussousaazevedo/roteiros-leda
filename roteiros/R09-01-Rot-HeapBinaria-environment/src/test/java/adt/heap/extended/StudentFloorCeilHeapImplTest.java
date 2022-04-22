package adt.heap.extended;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import adt.heap.ComparatorMaxHeap;
import adt.heap.ComparatorMinHeap;

public class StudentFloorCeilHeapImplTest {

	FloorCeilHeapImpl heap;
	Integer[] elements;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setUpMax() {
		heap = new FloorCeilHeapImpl(new ComparatorMaxHeap());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setUpMin() {
		heap = new FloorCeilHeapImpl(new ComparatorMinHeap());
	}
	
	public void setElements() {
		elements = new Integer[] {20, 25, 3, 14, 1, 100, 77, 81, -3};
	}
	
	@Test
	public void testExactFloor() {
		setUpMax();
		for (int rep = 0; rep < 2; rep++) {
			setElements();
			assertEquals(new Integer(-3), heap.floor(this.elements, -3));
			setElements();
			assertEquals(new Integer(81), heap.floor(this.elements, 81));
			setElements();
			assertEquals(new Integer(25), heap.floor(this.elements, 25));
			setUpMin();
		}
	}
	
	@Test
	public void testValidFloor() {
		setUpMax();
		for (int rep = 0; rep < 2; rep++) {
			setElements();
			assertEquals(new Integer(-3), heap.floor(this.elements, 0));
			setElements();
			assertEquals(new Integer(77), heap.floor(this.elements, 80));
			setElements();
			assertEquals(new Integer(14), heap.floor(this.elements, 18));
			setElements();
			assertEquals(new Integer(100), heap.floor(this.elements, 3000));
			setUpMin();
		}
	}
	
	@Test
	public void testInvalidFloor() {
		setUpMax();
		for (int rep = 0; rep < 2; rep++) {
			setElements();
			assertNull(heap.floor(this.elements, -4));
			setElements();
			assertNull(heap.floor(this.elements, -12));
			setElements();
			assertNull(heap.floor(this.elements, -100));
			setUpMin();
		}
	}
	
	// {20, 25, 3, 14, 1, 100, 77, 81, -3};
	@Test
	public void testExactCeil() {
		setUpMax();
		for (int rep = 0; rep < 2; rep++) {
			setElements();
			assertEquals(new Integer(-3), heap.ceil(this.elements, -3));
			setElements();
			assertEquals(new Integer(81), heap.ceil(this.elements, 81));
			setElements();
			assertEquals(new Integer(25), heap.ceil(this.elements, 25));
			setUpMin();
		}
	}
	
	@Test
	public void testValidCeil() {
		setUpMax();
		for (int rep = 0; rep < 2; rep++) {
			setElements();
			assertEquals(new Integer(1), heap.ceil(this.elements, 0));
			setElements();
			assertEquals(new Integer(81), heap.ceil(this.elements, 80));
			setElements();
			assertEquals(new Integer(20), heap.ceil(this.elements, 18));
			setElements();
			assertEquals(new Integer(-3), heap.ceil(this.elements, -3000));
			setUpMin();
		}
	}
	
	@Test
	public void testInvalidCeil() {
		setUpMax();
		for (int rep = 0; rep < 2; rep++) {
			setElements();
			assertNull(heap.ceil(this.elements, 101));
			setElements();
			assertNull(heap.ceil(this.elements, 200));
			setElements();
			assertNull(heap.ceil(this.elements, 250));
			setUpMin();
		}
	}
	
}
