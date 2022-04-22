package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		SingleLinkedListNode<T> actualNode = this.head;
		int counter = 0;
		while (!actualNode.isNIL()) {
			counter++;
			actualNode = actualNode.getNext();
		}
		return counter;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> actualNode = this.head;
		while (!actualNode.isNIL()) {	
			if (actualNode.getData() == element) {
				return element;
			}
			actualNode = actualNode.getNext();
		}
		return null;
	}

	@Override
	public void insert(T element) {
		if (element == null) { return; }
		
		SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
		if (this.isEmpty()) {
			this.setHead(newNode);
			return;
		}
		
		SingleLinkedListNode<T> actualNode = this.head;
		while (!actualNode.getNext().isNIL()) {
			actualNode = actualNode.getNext();
		}
		
		actualNode.setNext(newNode);
	}

	@Override
	public void remove(T element) {
		if (element == null || this.isEmpty()) { return; }
		if (this.getHead().getData() == element) {
			this.setHead(head.getNext());
			return;
		}
		
		SingleLinkedListNode<T> actualNode = this.head;
		while (!actualNode.isNIL()) {
			if (actualNode.getNext().getData() == element) {
				actualNode.setNext(actualNode.getNext().getNext());
				break;
			}
			actualNode = actualNode.getNext();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		SingleLinkedListNode<T> actualNode = this.head;
		T[] resp = (T[]) new Object[this.size()];
		
		for (int i = 0; i < resp.length; i++) {
			if (actualNode.isNIL()) { break; }
			
			resp[i] = actualNode.getData();
			actualNode = actualNode.getNext();
		}
		return resp;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
