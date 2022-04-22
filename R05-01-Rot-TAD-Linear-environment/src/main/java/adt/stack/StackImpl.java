package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if (this.isEmpty()) {
			return null;
		}
		return array[top];
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public boolean isFull() {
		return top == array.length-1;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull()) { throw new StackOverflowException(); }
		if (element == null) { return; }
		
		top++;
		this.array[top] = element;
		
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty()) { throw new StackUnderflowException(); }
		
		T removedElement = array[top];
		array[top] = null;
		top--;
		return removedElement;
		
	}
}
