package orderStatistic;

import java.util.Arrays;
import java.util.PriorityQueue;

import adt.heap.ComparatorMaxHeap;

public class OrderStatisticsHeapImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Existem diversas formas de se calcular uma estatistica de ordem. 
	 * Voce deve fazer isso usando uma heap restricoes:
	 * - nenhuma copia ou estrutura array auxiliar serah permitida, exceto o uso de
	 *   uma PriorityQueue
	 * - caso a estatistica de ordem procurada nao exista no array o metodo deve retornar null 
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public T getOrderStatistics(T[] array, int k) {
		PriorityQueue<T> heap = new PriorityQueue<T>(new ComparatorMaxHeap());
		T orderElement = null;
		
		heap.addAll(Arrays.asList(array));
		
		if (!(k < 0 || k > heap.size())) {
			for (int oeIndex = 1; oeIndex <= k; oeIndex++) {
				if (oeIndex != k) {
					heap.remove();
				} else {
					orderElement = heap.element();
					break;
				}
			}
		}
		return orderElement;
	}
}
