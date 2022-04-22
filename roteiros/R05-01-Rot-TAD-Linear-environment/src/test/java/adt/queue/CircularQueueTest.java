package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CircularQueueTest {

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
		queue1 = new CircularQueue<Integer>(4);
		queue2 = new CircularQueue<Integer>(2);
		queue3 = new CircularQueue<Integer>(3);
	}

	// MÉTODOS DE TESTE
	@Test
	public void testEnqueueCircular() {
		try {
			queue1.enqueue(new Integer(4));
			queue1.dequeue(); queue1.dequeue();
			queue1.enqueue(new Integer(5));
		} catch (QueueOverflowException e) {
			e.printStackTrace();
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(new Integer(3), queue1.head());
	}
	
	@Test
	public void testDequeueCircular() {
		
		try {
			queue1.enqueue(new Integer(4));
			queue1.dequeue(); queue1.dequeue();
			queue1.enqueue(new Integer(5)); queue1.enqueue(new Integer(6));
			queue1.dequeue(); queue1.dequeue(); queue1.dequeue();
		} catch (QueueOverflowException e) {
			e.printStackTrace();
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(new Integer(6), queue1.head());
	}
}
