package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) { throw new QueueOverflowException(); }
		if (element == null) { return; }
		
		if (this.isEmpty()) { head++; }
		tail = (tail + 1) % this.array.length;
		this.array[tail] = element;
		this.elements++;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) { throw new QueueUnderflowException(); }
		
		T removedElement = head();
		this.array[head] = null;
		this.elements--;
		
		if (this.isEmpty()) {
			this.head = -1;
			this.tail = -1;
		} else {
			this.head = (head + 1) % this.array.length;
		}
		
		return removedElement;
	}

	@Override
	public T head() {
		if (this.isEmpty()) { return null; }
		return this.array[head];
	}

	@Override
	public boolean isEmpty() {
		return this.elements == 0;
	}

	@Override
	public boolean isFull() {
		return this.elements == this.array.length;
	}

}
