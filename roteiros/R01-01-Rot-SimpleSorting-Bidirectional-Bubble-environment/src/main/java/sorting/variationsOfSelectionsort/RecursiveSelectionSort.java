package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

public class RecursiveSelectionSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * Implementação recursiva do selection sort. Você deve implementar apenas
	 * esse método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if (rightIndex > array.length-1 || leftIndex < 0 || leftIndex >= rightIndex) {return;}
		
		int chosenIndex = leftIndex;
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(array[chosenIndex]) < 0) {
				chosenIndex = i;
			}
		}
		Util.swap(array, leftIndex, chosenIndex);
		
		sort(array, leftIndex+1, rightIndex);
	}

}
