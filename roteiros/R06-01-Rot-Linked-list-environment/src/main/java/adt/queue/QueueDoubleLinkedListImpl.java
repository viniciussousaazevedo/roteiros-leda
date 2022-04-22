package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.SingleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element == null) { return; }
		if (this.isFull()) { throw new QueueOverflowException(); }
		
		this.list.insert(element);
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) { throw new QueueUnderflowException(); }
		T removedElement = this.head();
		this.list.removeFirst();
		
		return removedElement;
	}

	@Override
	public T head() {
		if (this.isEmpty()) { return null; }
		return ((SingleLinkedListImpl<T>) this.list).getHead().getData();
	}

	@Override
	public boolean isEmpty() {
		return this.list.size() == 0;
	}

	@Override
	public boolean isFull() {
		return this.list.size() == this.size;
	}

}
