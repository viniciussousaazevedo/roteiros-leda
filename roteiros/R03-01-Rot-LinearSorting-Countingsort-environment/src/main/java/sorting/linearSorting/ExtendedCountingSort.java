package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if (rightIndex > array.length-1 || leftIndex < 0 || leftIndex >= rightIndex || array.length == 1) {
			return;
		}
		
		Integer[] auxArray;
		Integer[] sortedArray = new Integer[rightIndex-leftIndex+1];
		Integer biggestNumber = array[leftIndex];
		Integer smallestNumber = array[leftIndex];
		
		// Buscando o maior elemento para inicializar auxArray
		for (int i = leftIndex+1; i <= rightIndex; i++) {
			if (array[i] > biggestNumber) {
				biggestNumber = array[i];
			} else if (array[i] < smallestNumber) {
				smallestNumber = array[i];
			}
		}
		
		auxArray = new Integer[Math.abs(biggestNumber - smallestNumber) + 1];
		
		// Atribuindo zero aos elementos de auxArray para incrementá-los
		for (int i = 0; i < auxArray.length; i++) {
			auxArray[i] = 0;
		}
		
		// Inserindo a presença dos valores de array em auxArray
		for (int i = leftIndex; i <= rightIndex; i++) {
			auxArray[array[i]-smallestNumber]++;
		}
		
		// Adaptando auxArray para criar a lista ordenada
		for (int i = 1; i < auxArray.length; i++) {
			auxArray[i] += auxArray[i-1];
		}
		
		// Inserindo valores ordenados em sortedArray
		for (int i = rightIndex; i >= leftIndex; i--) {
			sortedArray[auxArray[array[i]-smallestNumber]-1] = array[i];
			auxArray[array[i]-smallestNumber]--;
		}
		
		
		int j = 0;
		// Inserindo valores de sortedArray em array
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = sortedArray[j];
			j++;
		}
	}
}
