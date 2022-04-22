package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentQueueTest {

	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;

	@Before
	public void setUp() throws QueueOverflowException {

		getImplementations();

		// Fila com 3 elementos não cheia.
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);

		// Fila com 2 elementos de tamanho 2. Fila cheia.
		queue2.enqueue(1);
		queue2.enqueue(2);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		queue1 = new QueueUsingStack<Integer>(4);
		queue2 = new QueueUsingStack<Integer>(2);
		queue3 = new QueueUsingStack<Integer>(0);
	}

	// MÉTODOS DE TESTE
	@Test
	public void testHead() {
		assertEquals(new Integer(1), queue1.head());
		assertEquals(new Integer(1), queue2.head());
		assertNull(queue3.head());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(queue1.isEmpty());
		assertFalse(queue2.isEmpty());
		assertTrue(queue3.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(queue1.isFull());
		assertTrue(queue2.isFull());
		assertTrue(queue3.isFull());
	}

	@Test
	public void testEnqueue() {
		try {
			queue1.enqueue(new Integer(5));
		} catch (QueueOverflowException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNullEnqueue() {
		try {
			queue1.enqueue(null);
			assertEquals(new Integer(1), queue1.dequeue());
		} catch (QueueOverflowException e) {
			e.printStackTrace();
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		queue1.enqueue(new Integer(5));
		queue1.enqueue(new Integer(6)); // exceção lançada
		
		queue2.enqueue(new Integer(3)); // exceção lançada
		
		queue3.enqueue(new Integer(1)); // exceção lançada
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(new Integer(1), queue1.dequeue());
			assertEquals(new Integer(1), queue2.dequeue());
		} catch (QueueUnderflowException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		assertEquals(new Integer(1), queue1.dequeue());
		assertEquals(new Integer(2), queue1.dequeue());
		assertEquals(new Integer(3), queue1.dequeue());
		assertEquals(new Integer(4), queue1.dequeue()); // exceção lançada
		
		assertEquals(new Integer(1), queue2.dequeue());
		assertEquals(new Integer(2), queue2.dequeue());
		assertEquals(new Integer(3), queue2.dequeue()); // exceção lançada
		
		assertEquals(new Integer(1), queue3.dequeue());
	}
}