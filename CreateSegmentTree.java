import java.util.Scanner;

class CreateSegmentTree {

	public static void buildTree(int[] arr, int s, int e, int[] tree, int index) {

		if (s == e) {
			tree[index] = arr[s];
			return;
		}
		int mid = (s + e) / 2;
		buildTree(arr, s, mid, tree, index * 2);
		buildTree(arr, mid + 1, e, tree, (index * 2) + 1);
		tree[index] = Math.min(tree[index * 2], tree[(index * 2) + 1]);
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		int[] tree = new int[(4 * n) + 1];

		buildTree(arr, 0, arr.length - 1, tree, 1);
		for (int i = 0; i < tree.length; i++) {
			System.out.print(tree[i] + " ");
		}
	}
}