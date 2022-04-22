package orderStatistic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class StudentOrderStatisticsHeapImplTest {

	OrderStatisticsHeapImpl<Integer> heap;
	Integer[] elements;

	@Before
	public void setUp() {
		heap = new OrderStatisticsHeapImpl<>();
	}
	
	public void setElements() {
		elements = new Integer[] {20, 25, 3, 14, 1, 100, 77, 81, -3};
	}
	
	
	@Test
	public void testValidOrderStatistics() {
		// {-3, 1, 3, 14, 20, 25, 77, 81, 100}
		setElements();
		assertEquals(new Integer(-3), heap.getOrderStatistics(elements, 1));
		assertEquals(new Integer(14), heap.getOrderStatistics(elements, 4));
		assertEquals(new Integer(100), heap.getOrderStatistics(elements, 9));
		assertEquals(new Integer(77), heap.getOrderStatistics(elements, 7));
	}
	
	@Test
	public void testInvalidOrderStatistics() {
		setElements();
		assertNull(heap.getOrderStatistics(elements, -3));
		assertNull(heap.getOrderStatistics(elements, 0));
		assertNull(heap.getOrderStatistics(elements, 10));
		assertNull(heap.getOrderStatistics(elements, 35));
	}
}
