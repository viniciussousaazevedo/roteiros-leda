package adt.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentStackTest {

	public Stack<Integer> stack1;
	public Stack<Integer> stack2;
	public Stack<Integer> stack3;

	@Before
	public void setUp() throws StackOverflowException {

		getImplementations();

		// Pilha com 3 elementos não cheia.
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);

		// Pilha com 2 elementos de tamanho 2, pilha cheia.
		stack2.push(1);
		stack2.push(2);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		stack1 = new StackDoubleLinkedListImpl<Integer>(4);
		stack2 = new StackDoubleLinkedListImpl<Integer>(2);
		stack3 = new StackDoubleLinkedListImpl<Integer>(0);
	}

	// MÉTODOS DE TESTE
	@Test
	public void testTop() {
		assertEquals(new Integer(3), stack1.top());
		assertEquals(new Integer(2), stack2.top());
		assertNull(stack3.top());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(stack1.isEmpty());
		assertFalse(stack2.isEmpty());
		assertTrue(stack3.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(stack1.isFull());
		assertTrue(stack2.isFull());
		assertTrue(stack3.isFull());
	}

	@Test
	public void testPush() throws StackOverflowException {
		stack1.push(new Integer(4));
	}
	
	@Test
	public void testPushNull() throws StackOverflowException {
		stack1.push(null);
		assertEquals(new Integer(3), stack1.top());
	}

	@Test(expected = StackOverflowException.class)
	public void testPushComErro() throws StackOverflowException {
		stack1.push(new Integer(5));
		stack1.push(new Integer(6)); // exceção lançada
		
		stack2.push(null); //exceção lançada
		stack3.push(new Integer(7)); // exceção lançada
	}

	@Test
	public void testPop() throws StackUnderflowException {
		assertEquals(new Integer(3), stack1.pop());
		assertEquals(new Integer(2), stack2.pop());
	}

	@Test(expected = StackUnderflowException.class)
	public void testPopComErro() throws StackUnderflowException {
		assertEquals(new Integer(3), stack1.pop());
		assertEquals(new Integer(2), stack1.pop());
		assertEquals(new Integer(1), stack1.pop());
		assertEquals(new Integer(0), stack1.pop()); // exceção lançada
		
		assertEquals(new Integer(2), stack2.pop());
		assertEquals(new Integer(1), stack2.pop());
		assertNull(stack2.pop()); // exceção lançada
		
		assertNull(stack3.pop()); // exceção lançada
	}
}