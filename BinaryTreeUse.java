import java.util.*;
public class BinaryTreeUse {

	public static int numNodes(BinaryTreeNode<Integer> root) {
		if (root == null) return 0;

		return 1 + numNodes(root.left) + numNodes(root.right);
	}

	public static BinaryTreeNode<Integer> takeTreeInputBetter(boolean isRoot, int parentData, boolean isLeft) {

		if (isRoot) {
			System.out.println("Enter root Data");
		}
		else {
			 if (isLeft) {
			 	System.out.println("Enter left Child of : " + parentData);
			 }
			 else {
			 	System.out.println("Enter right child of : " + parentData);
			 }
		}
		Scanner s = new Scanner(System.in);
		int rootData = s.nextInt();

		if(rootData == -1) return null;

		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
		BinaryTreeNode<Integer> leftChild = takeTreeInputBetter(false, rootData, true);
		BinaryTreeNode<Integer> rightChild = takeTreeInputBetter(false, rootData, false);
		root.left = leftChild;
		root.right = rightChild;
		return root;
	}

	// first left input then right input
	public static BinaryTreeNode<Integer> takeTreeInput() {
		System.out.println("Enter root data: ");
		Scanner s = new Scanner(System.in);
		int rootData = s.nextInt();

		if(rootData == -1) return null;

		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
		BinaryTreeNode<Integer> leftChild = takeTreeInput();
		BinaryTreeNode<Integer> rightChild = takeTreeInput();
		root.left = leftChild;
		root.right = rightChild;
		return root;
	}

	public static void printTreeDetailed(BinaryTreeNode<Integer> root) {
		if (root == null) return;
		
		System.out.print(root.data + ": ");
		if (root.left != null) System.out.print(root.left.data + ", ");
		if (root.right != null) System.out.print(root.right.data + " ");
		System.out.println();
		printTreeDetailed(root.left);
		printTreeDetailed(root.right);
	}

	public static void printTree(BinaryTreeNode<Integer> root) {
		if (root == null) return;

		System.out.println(root.data);
		printTree(root.left);
		printTree(root.right);
	}

	public static void main(String[] args) {
		// BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1);

		// BinaryTreeNode<Integer> rootLeft = new BinaryTreeNode<>(2);
		// BinaryTreeNode<Integer> rootRight = new BinaryTreeNode<>(3);

		// root.left = rootLeft;
		// root.right = rootRight;
		
		// BinaryTreeNode<Integer> twoRight = new BinaryTreeNode<>(4);
		// BinaryTreeNode<Integer> threeLeft = new BinaryTreeNode<>(5);

		// rootLeft.right = twoRight;
		// rootRight.left = threeLeft;
		BinaryTreeNode<Integer> root = takeTreeInputBetter(true, 0, true);
		printTreeDetailed(root);
		System.out.println(numNodes(root));
	}
}