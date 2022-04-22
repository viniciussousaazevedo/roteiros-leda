package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1; // Guarda os elementos invertidos
	private Stack<T> stack2; // Recebe os elementos temporariamente para relizar operações

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) { throw new QueueOverflowException(); }
		if (element == null) { return; }
		
		try {
			stack1.push(element);
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		
		if (this.isEmpty()) { throw new QueueUnderflowException(); }
		
		T removedElement = null;
		
		try {
			this.switchStack();
			removedElement = this.stack2.pop();
			this.switchStack();
		} catch (StackOverflowException | StackUnderflowException e) {
			e.printStackTrace();
		}
		
		return removedElement;
	}

	@Override
	public T head() {
		if (this.isEmpty()) { return null; }
		
		T head = null;
		
		try {
			this.switchStack();
			head = this.stack2.top();
			this.switchStack();
		} catch (StackOverflowException | StackUnderflowException e) {
			e.printStackTrace();
		}
		
		return head;
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}

	private void switchStack() throws StackOverflowException, StackUnderflowException {
		
		if (stack1.isEmpty() && stack2.isEmpty()) { return; }
		
		if (stack1.isEmpty()) {
			
			while (!stack2.isEmpty()) {
				this.stack1.push(stack2.pop());
			}
		} else if (stack2.isEmpty()) {
			
			while (!stack1.isEmpty()) {
				this.stack2.push(stack1.pop());
			}
		}
	}
}
