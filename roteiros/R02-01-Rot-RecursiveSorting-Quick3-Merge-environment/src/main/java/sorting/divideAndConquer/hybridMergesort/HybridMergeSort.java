package sorting.divideAndConquer.hybridMergesort;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	
	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
		
		if (rightIndex > array.length-1 || leftIndex < 0 || leftIndex >= rightIndex || array.length == 1) {
			return;
		}
		
		hybridMergeSort(array, leftIndex, rightIndex);
	}

	private void hybridMergeSort(T[] array, int leftIndex, int rightIndex) {
		
		if (rightIndex - leftIndex > SIZE_LIMIT) {
			if (leftIndex >= rightIndex) {
				return;
			}
			
			int middleIndex = (int) (leftIndex + rightIndex) / 2;
				
			hybridMergeSort(array, leftIndex, middleIndex);
			hybridMergeSort(array, middleIndex+1, rightIndex);
				
			merge(array, leftIndex, middleIndex, rightIndex);
			MERGESORT_APPLICATIONS++;
			
		} else {
			int actualIndex;
			for (int i = leftIndex+1; i <= rightIndex; i++) {
				actualIndex = i;
				
				while (actualIndex > leftIndex && array[actualIndex].compareTo(array[actualIndex-1]) < 0) {
					Util.swap(array, actualIndex, actualIndex-1);
					actualIndex--;
				}
			}
			INSERTIONSORT_APPLICATIONS++;
		}	
	}
	
	private void merge(T[] array, int leftIndex, int middleIndex, int rightIndex) {
		
		T[] auxArray = array.clone();
		
		int i = leftIndex;
		int j = middleIndex+1;
		int k = leftIndex;
		
		while (i <= middleIndex && j <= rightIndex) {
			
			if (auxArray[i].compareTo(auxArray[j]) <= 0) {
				array[k] = auxArray[i];
				i++;
			} else {
				array[k] = auxArray[j];
				j++;
			}
			k++;
		}
		
		while (i <= middleIndex) {
			array[k] = auxArray[i];
			i++; k++;
		}
		
		while (j <= rightIndex) {
			array[k] = auxArray[j];
			j++; k++;
		}
	}
}
