package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		if (element == null) { return; }
		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) this.head, new DoubleLinkedListNode<T>());
		((DoubleLinkedListNode<T>) this.head).setPrevious(newNode);
		if (this.isEmpty()) {
			this.last = newNode;
		}
		this.head = newNode;
	}

	@Override
	public void removeFirst() {
		if (this.isEmpty()) { return; }
		
		this.head = this.head.getNext();
		if (this.head.isNIL()) { 
			this.last = (DoubleLinkedListNode<T>) this.head;
		} else {
			((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<T>());
		}
	}

	@Override
	public void removeLast() {
		if (this.isEmpty()) { return; }
		
		if (this.head == this.last) {
			this.head = new DoubleLinkedListNode<T>();
			this.last = (DoubleLinkedListNode<T>) this.head;
		} else {
			this.last.getPrevious().setNext(new DoubleLinkedListNode<T>());
			this.last = this.last.getPrevious();
		}
	}
	
	@Override
	public T search(T element) {
		if (element == null) { return null; }
		
		SingleLinkedListNode<T> actualNode = this.head;
		
		while (actualNode.getData() != element) {
			if (actualNode.isNIL()) { break; }
			actualNode = actualNode.getNext();
		}
		return actualNode.getData();
	}
	
	@Override
	public void insert(T element) {
		if (element == null) { return; }
		
		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), this.last);
		if (this.isEmpty()) {
			this.head = newNode;
		} else {
			this.last.setNext(newNode);	
		}
		this.last = newNode;
	}
	
	@Override
	public void remove(T element) {
		if (element == null || this.isEmpty()) { return; }
		
		if (this.head.getData() == element) {
			this.removeFirst();
		} else {
			DoubleLinkedListNode<T> actualNode = (DoubleLinkedListNode<T>) this.head;
			while (actualNode.getNext().getData() != element) {
				actualNode = (DoubleLinkedListNode<T>) actualNode.getNext();
				if (actualNode.isNIL()) { return; }
			}
			
			actualNode.setNext(actualNode.getNext().getNext());
			((DoubleLinkedListNode<T>) actualNode.getNext()).setPrevious(actualNode);
			if (actualNode.getNext().isNIL()) { this.last = actualNode; }
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
