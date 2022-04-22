package adt.bst;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	@SuppressWarnings("unused")
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		return isBST(this.bst.getRoot());
	}

	private boolean isBST(BSTNode<T> node) {
		boolean isBST = true;
		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty()) {
				if (node.getLeft().getData().compareTo(node.getData()) < 0) {
					isBST = isBST((BSTNode<T>) node.getLeft());
				} else {
					isBST = false;
				}
			}
			
			if (isBST && !node.getRight().isEmpty()) {
				if (node.getRight().getData().compareTo(node.getData()) > 0) {
					isBST = isBST((BSTNode<T>) node.getRight());
				} else {
					isBST = false;
				}
			}
		}
		return isBST;
	}
}
