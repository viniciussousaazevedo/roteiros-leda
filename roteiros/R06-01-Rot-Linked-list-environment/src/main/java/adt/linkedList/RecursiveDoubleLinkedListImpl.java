package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insertFirst(T element) {
		if (element == null) { return; }
		
		if (this.isEmpty()) {
			this.setData(element);
			
			this.setNext(new RecursiveDoubleLinkedListImpl<T>()); // Next == NIL
			((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this);
			
			this.setPrevious(new RecursiveDoubleLinkedListImpl<T>()); // Prev == NIL
			this.getPrevious().setNext(this);
		} else {
			
			RecursiveDoubleLinkedListImpl<T> auxHead = new RecursiveDoubleLinkedListImpl<T>();
			auxHead.data = this.data;
			auxHead.next = this.next;
			((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(auxHead);
			this.next = auxHead;
			((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this);
			this.data = element;
			auxHead.previous = this;
		}
	}

	@Override
	public void removeFirst() {
		if (this.isEmpty()) { return; }
		remove(this.data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeLast() {
		if (this.isEmpty()) { return; }
		if (this.next.isEmpty()) { 
			this.remove(data);
		} else {
			((DoubleLinkedList<T>) this.next).removeLast();
		}
	}
	
	@Override
	public void insert(T element) {
		if (element == null) { return; }
		
		if (this.isEmpty()) {
			this.data = element;
			this.next = new RecursiveDoubleLinkedListImpl<T>();
			((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this);
			
			if (this.previous == null) {
				this.previous = new RecursiveDoubleLinkedListImpl<T>();
				this.previous.setNext(this);
			}
		} else {
			this.next.insert(element);
		}
	}
	
	@Override
	public void remove(T element) {
		if (element == null || this.isEmpty()) { return; }
		
		if (this.getData() == element) {
			this.setData(this.getNext().getData());
			this.setNext(this.getNext().getNext());
			this.setPrevious(this.previous);
		} else {
			this.getNext().remove(element);
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
