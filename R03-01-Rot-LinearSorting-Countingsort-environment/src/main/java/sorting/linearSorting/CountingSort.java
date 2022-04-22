package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Voce pode assumir que o maior inteiro armazenado não chega a 100.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if (rightIndex > array.length-1 || leftIndex < 0 || leftIndex >= rightIndex || array.length == 1) {
			return;
		}
		
		Integer[] auxArray;
		Integer[] sortedArray = new Integer[rightIndex-leftIndex+1];
		Integer biggestNumber = array[leftIndex];
		
		// Buscando o maior elemento para inicializar auxArray
		for (int i = leftIndex+1; i <= rightIndex; i++) {
			if (array[i] > biggestNumber) {
				biggestNumber = array[i];
			}
		}
		
		auxArray = new Integer[biggestNumber+1];
		
		// Atribuindo zero aos elementos de auxArray para incrementá-los
		for (int i = 0; i < auxArray.length; i++) {
			auxArray[i] = 0;
		}
		
		// Inserindo a presença dos valores de array em auxArray
		for (int i = leftIndex; i <= rightIndex; i++) {
			auxArray[array[i]]++;
		}
		
		// Adaptando auxArray para criar a lista ordenada
		for (int i = 1; i < auxArray.length; i++) {
			auxArray[i] += auxArray[i-1];
		}
		
		// Inserindo valores ordenados em sortedArray
		for (int i = rightIndex; i >= leftIndex; i--) {
			sortedArray[auxArray[array[i]]-1] = array[i];
			auxArray[array[i]]--;
		}
		
		
		int j = 0;
		// Inserindo valores de sortedArray em array
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = sortedArray[j];
			j++;
		}
	}
}
