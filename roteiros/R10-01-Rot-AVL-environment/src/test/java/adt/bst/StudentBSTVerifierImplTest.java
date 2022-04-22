package adt.bst;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentBSTVerifierImplTest {

	private BSTVerifier<Integer> verifier;

	private BSTImpl<Integer> setUpBST(Integer[] elements) {
		BSTImpl<Integer> bst = new BSTImpl<>();
		for (int i : elements) {
			bst.insert(i);
		}
		return bst;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setUpVerifier(Integer[] elements) {
		this.verifier = new BSTVerifierImpl(setUpBST(elements));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setUpVerifier(BSTImpl<Integer> bst) {
		this.verifier = new BSTVerifierImpl(bst);
	}
	
	@Test
	public void testIsBST() {
		setUpVerifier(new Integer[] {3, 5, 20, 27, 80});
		assertTrue(verifier.isBST());
		
		setUpVerifier(new Integer[] {50, 20, 1, 40, 90, 93, 120});
		assertTrue(verifier.isBST());
		
		setUpVerifier(new Integer[] {50, 1, 88, 34, 455, 20, -15, 9, 31});
		assertTrue(verifier.isBST());
		
		setUpVerifier(new Integer[] {});
		assertTrue(verifier.isBST());
	}
	
	@Test
	public void testIsNotBST() {
		BSTImpl<Integer> bst = setUpBST(new Integer[] {3, 5, 20, 27, 80});
		bst.getRoot().getLeft().setData(99);
		setUpVerifier(bst);
		assertFalse(verifier.isBST());
		
		bst = setUpBST(new Integer[] {50, 20, 1, 40, 90, 93, 120});
		bst.getRoot().setData(200);
		setUpVerifier(bst);
		assertFalse(verifier.isBST());
		
		bst = setUpBST(new Integer[] {50, 1, 88, 34, 455, 20, -15, 9, 31});
		bst.getRoot().getRight().getLeft().setData(-1);
		bst.getRoot().getLeft().getLeft().setData(120);
		setUpVerifier(bst);
		assertFalse(verifier.isBST());
	}
}
