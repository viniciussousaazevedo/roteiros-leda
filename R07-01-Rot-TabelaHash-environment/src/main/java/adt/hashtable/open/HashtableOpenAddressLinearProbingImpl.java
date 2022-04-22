package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) throws HashtableOverflowException {
		if (element != null) {
			int hashIndex;
			for (int probe = 0; probe < this.capacity(); probe++) {
				hashIndex = ((HashFunctionOpenAddress<T>) this.getHashFunction()).hash(element, probe);
					
				if (this.table[hashIndex] == null || this.table[hashIndex].equals(new DELETED())) {
					this.table[hashIndex] = element;
					this.elements++;
					return;
				} else {
					this.COLLISIONS++;
				}
			}
			throw new HashtableOverflowException();
		}
	}

	@Override
	public void remove(T element) {
		int elementIndex = indexOf(element);
		if (element != null && elementIndex != -1) {
			
			this.table[elementIndex] = new DELETED();
			this.elements--;
		}
	}

	@Override
	public T search(T element) {
		if (indexOf(element) == -1) { return null; }
		return element;
	}

	@Override
	public int indexOf(T element) {
		int elementIndex = -1;
		if (element != null) {
			int hashIndex;
			
			for (int probe = 0; probe < this.capacity(); probe++) {
				hashIndex = ((HashFunctionOpenAddress<T>) this.getHashFunction()).hash(element, probe);
				if (this.table[hashIndex] == null) { break; }
				if (this.table[hashIndex].equals(element)) {
					elementIndex = hashIndex;
					break;
				}
			}
		}
		return elementIndex;
	}
}
