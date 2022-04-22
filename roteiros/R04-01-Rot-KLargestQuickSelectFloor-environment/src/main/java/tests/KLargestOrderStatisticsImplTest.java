package tests;

import java.util.Arrays;
import java.util.Scanner;

import orderStatistic.KLargestOrderStatisticsImpl;

public class KLargestOrderStatisticsImplTest {

	public static void main(String[] args) {
		KLargestOrderStatisticsImpl<Integer> implementation = new KLargestOrderStatisticsImpl<Integer>();
		Integer[] integerArray = new Integer[] {8, 20, 30, 1, 6, 3, 9, 0};
		int k = 4;
		
		System.out.println(Arrays.toString(implementation.getKLargest(integerArray, k)));
	}
}
