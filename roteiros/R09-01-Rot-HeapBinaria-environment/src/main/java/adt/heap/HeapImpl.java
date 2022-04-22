package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o menor sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 2 < 3),
 * essa heap deixa os elementos menores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap. OU seja, voce deve considerar que a heap usa o comparator
	 * interno e se o comparator responde compare(x,y) < 0 entao o x eh menor
	 * e sobe na heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}
	
	private boolean hasLeft(int i) {
		return (left(i) <= index);
	}
	
	private boolean hasRight(int i) {
		return (right(i) <= index);
	}
	
	private boolean hasChildren(int i) {
		return hasLeft(i) && hasRight(i);
	}
	
	private boolean isLeaf(int i) {
		return !(hasLeft(i) || hasRight(i));
	}
	
	private boolean hasParent(int i) {
		return i != 0;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[])resp.toArray(new Comparable[0]);
	}

	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve usar o comparator
	 * para subir os elementos na heap.
	 */
	private void heapify(int position) {
		if (!isLeaf(position)) {
			if (hasChildren(position) &&
					comparator.compare(heap[position], heap[left(position)]) < 0 &&
					comparator.compare(heap[position], heap[right(position)]) < 0) {
				if (comparator.compare(heap[left(position)], heap[right(position)]) > 0) {
					Util.swap(heap, position, left(position));
					heapify(left(position));
				} else {
					Util.swap(heap, position, right(position));
					heapify(right(position));
				}
			} else if (hasLeft(position) &&
					comparator.compare(heap[position], heap[left(position)]) < 0) {
				Util.swap(heap, position, left(position));
				heapify(left(position));
			} else if (hasRight(position) &&
					comparator.compare(heap[position], heap[right(position)]) < 0) {
				Util.swap(heap, position, right(position));
				heapify(right(position));
			}
		}
	}

	@Override
	public void insert(T element) {
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		
		this.index++;
		this.heap[index] = element;
		allocateNewElement(index);
	}

	private void allocateNewElement(int index) {
		if (hasParent(index)) {
			if (this.comparator.compare(heap[index], heap[parent(index)]) > 0) {
				Util.swap(heap, index, parent(index));
				allocateNewElement(parent(index));
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buildHeap(T[] array) {
		heap = (T[]) (new Comparable[array.length]);
		
		for (int i = 0; i < array.length; i++) {
			heap[i] = array[i]; // Isso foi feito para que o heap não aponte para o mesmo lugar que o array, permitindo modificações ainda no array
		}
		
		this.index = heap.length-1;
		
		if (hasParent(index)) {
			for (int i = parent(index); i >= 0; i--) {
				this.heapify(i);
			}
		}
	}

	@Override
	public T extractRootElement() {
		T rootElement = null;
		
		if (!this.isEmpty()) {
			rootElement = this.rootElement();
			Util.swap(heap, 0, this.index);
			this.heap[index] = null;
			this.index--;
			this.heapify(0);
		}
		
		return rootElement;
	}

	@Override
	public T rootElement() {
		T rootElement = null;
		
		if (!this.isEmpty()) {
			rootElement = this.heap[0];
		}
		
		return rootElement;
	}

	@Override
	public T[] heapsort(T[] array) {
		this.buildHeap(array);
		
		int sortedIndex = this.index;
		boolean isMinHeap = false;
		
		if (this.rootElement().compareTo(this.heap[index]) < 0) {
			sortedIndex = 0;
			isMinHeap= true;
		}
		
		while (!this.isEmpty()) {
			Util.swap(heap, 0, index);
			array[sortedIndex] = this.heap[index];
			this.index--;
			this.heapify(0);
			
			if (isMinHeap) {
				sortedIndex++;
			} else {
				sortedIndex--;
			}
		}
		return array;
	}


	@Override
	public int size() {
		return index+1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
