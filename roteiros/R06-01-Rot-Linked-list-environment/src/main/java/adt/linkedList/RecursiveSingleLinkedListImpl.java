package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if (this.isEmpty()) { return 0; }
		return size(0);
	}
	
	private int size(int counter) {
		counter++;
		if (!this.next.isEmpty()) {
			return this.next.size(counter);
		}
		return counter;
	}

	@Override
	public T search(T element) {
		if (this.isEmpty()) { return null; }
		
		if (this.getData() == element) {
			return element;
		} else {
			return this.getNext().search(element);
		}
	}

	@Override
	public void insert(T element) {
		if (element == null) { return; }
		
		if (this.isEmpty()) {
			this.data = element;
			this.next = new RecursiveDoubleLinkedListImpl<T>();
		} else {
			this.next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (this.isEmpty() || element == null) { return; }
		
		if (this.getData() == element) {
			this.setData(this.getNext().getData());
			this.setNext(this.getNext().getNext());
		} else {
			this.getNext().remove(element);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] resp = (T[]) new Object[this.size()];
		if (this.isEmpty()) { return resp; }
		
		return toArray(0, resp);
	}
	
	private T[] toArray(int index, T[] resp) {
		
		resp[index] = data;
		
		if (index == resp.length-1) { 
			return resp; 
		} else {
			return this.getNext().toArray(index+1, resp);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
