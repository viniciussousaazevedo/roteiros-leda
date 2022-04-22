package recursao;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
		return calcularSomaArray(array, 0);
	}
	
	public int calcularSomaArray(int[] array, int index) {
		int result = 0;
		
		if (array.length != 0) {
			if (index == array.length-1) {
				result = array[index];
			} else {
				result = array[index] + calcularSomaArray(array, index+1);
			}
		}
		return result;
	}
	
	public long calcularFatorial(int n) {
		long result = 1;
		
		if (n != 0 && n != 1) {
			result = n * calcularFatorial(n-1);
			System.out.println(result);
		}
		return result;
	}

	public int calcularFibonacci(int n) {
		int result = 1;
		
		if (n != 1 && n != 2) {
			result = calcularFibonacci(n-1) + calcularFibonacci(n-2);
		}
		
		return result;
	}

	public int countNotNull(Object[] array) {
		return countNotNull(array, 0);
	}
	
	public int countNotNull(Object[] array, int index) {
		int result = 0;
		
		if (array.length != 0) {
			if (index == array.length-1) {
				if (array[index] == null) {
					result = 1;
				}
			} else {
				if (array[index] == null) {
					result = 1 + countNotNull(array, index+1);
				} else {
					result = countNotNull(array, index+1);
				}
			}
		}
		return result;
	}

	public long potenciaDe2(int expoente) {
		long result = 1;
		
		if (expoente != 0) {
			result = 2 * potenciaDe2(expoente-1);
		}
		
		return result;
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result;
		
		if (n == 1) {
			result = termoInicial;
		} else {
			result = razao + progressaoAritmetica(termoInicial, razao, n-1);
		}
		
		return result;
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result;
		
		if (n == 1) {
			result = termoInicial;
		} else {
			result = razao * progressaoGeometrica(termoInicial, razao, n-1);
		}
		
		return result;
	}
	
	
}
