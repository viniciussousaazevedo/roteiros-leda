package orderStatistic;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Voce pode modificar o array original
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem.
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo,
 *   ao solicitar os 5 maiores elementos em um array que soh contem 3 elementos).
 *   Este metodo NUNCA deve retornar null.
 * 
 * @author campelo and adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		
		// Tratamento de valores inválidos de k
		if (k <= 0 || k > array.length) {
			return (T[]) new Integer[0];
		}
		
		// caso o array tenha tamanho 1
		if (array.length == 1) {
			return array;
		}
		
		// descoberta dos valores com base na estatística de ordem (passando array.lenght-k+1)
		T orderElement = orderStatistics(array, array.length-k+1);
		
		T[] finalArray;
		int count = 0;
		
		// anulando valores invalidos
		for (int i = 0; i < array.length; i++) {
			if (array[i].compareTo(orderElement) < 0) {
				array[i] = null;
			} else {
				count++;
			}
		}
		
		// passando valores validos para um novo array
		finalArray = (T[]) new Integer[count];
		int index = 0;
		for (T value : array) {
			if (value != null) {
				finalArray[index] = value;
				index++;
			}
		}
		
		return finalArray;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
			
		// descobindo o valor da estatística de ordem com base em quick selection
		Integer eo = quickSelection(array, 0, array.length-1, k);
		
		
		if (eo != k-1) {
			return null;
		}
		
		return array[eo];
	}
	
	private Integer quickSelection(T[] array, int leftIndex, int rightIndex, int k) {
		
		if (rightIndex > array.length-1 || leftIndex < 0 || leftIndex > rightIndex) {
			return null;
		}
		
		int pivot = partition(array, leftIndex, rightIndex, leftIndex);
		
		if (pivot > k-1) {
			return quickSelection(array, leftIndex, pivot-1, k);
		} else if (pivot < k-1) {
			return quickSelection(array, pivot+1, rightIndex, k);
		}
		
		return pivot;
	}
	
	private int partition(T[] array, int leftIndex, int rightIndex, int pivot) {
		
		int partitionIndex = pivot;
		
		for (int i = leftIndex+1; i <= rightIndex; i++) {
			
			if (array[i].compareTo(array[pivot]) <= 0) {
				partitionIndex++;
				Util.swap(array, i, partitionIndex);
			}
		}
		Util.swap(array, pivot, partitionIndex);
		return partitionIndex;
	}
}