package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		BSTImpl<Integer> tree = new BSTImpl<>();
		
		if (recursiveInsert(tree, array, 0, (int) numero)) {
			return (int) numero;
		} else {
			tree.insert((int) numero);
			BSTNode<Integer> predecessor = tree.predecessor((int) numero);
			return (predecessor == null ? null : predecessor.getData());
		}
	}

	private boolean recursiveInsert(BSTImpl<Integer> tree, Integer[] array, int i, int numero) {
		boolean isNumberInArray = false;
		if (i < array.length) {
			if (array[i] != numero) {
				tree.insert(array[i]);
			} else {
				isNumberInArray = true;
			}
			
			if (!isNumberInArray) {
				isNumberInArray = recursiveInsert(tree, array, i+1, numero); 
			}
		}
		return isNumberInArray;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		BSTImpl<Integer> tree = new BSTImpl<>();
		if (recursiveInsert(tree, array, 0, (int) numero)) {
			return (int) numero;
		} else {
			tree.insert((int) numero);
			BSTNode<Integer> sucessor = tree.sucessor((int) numero);
			return (sucessor == null ? null : sucessor.getData());
		}
	}
}
