import java.util.Scanner;

class RangeUpdateSegentTree {
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
	public static int query(int[] tree, int s, int e, int sq, int eq, int index) {
		
		// complete overlap
		if (sq <= s && eq >= e)  {
			return tree[index];
		}

		// no overlap
		if ((eq < s) || (sq > e)) {
			return Integer.MAX_VALUE;
		}

		// partial overlap
		int mid = (s + e) / 2;
		int left = query(tree, s, mid, sq, eq, 2 * index);
		int right = query(tree, mid + 1, e, sq, eq, 2 * index + 1);

		return Math.min(left, right);

	}
	public static void update(int[] tree, int ss, int se, int i, int increment, int index) {
		// case where the I is out of the bounds
		if (i > se || i < ss) {
			return;
		}
		// leaf nodes
		if (ss == se) {
			tree[index] += increment;
			return;
		}
		// otherwise
		int mid = (ss + se) / 2;
		update(tree, ss, mid, i, increment, 2 * index);
		update(tree, mid + 1, se, i, increment, 2 * index + 1);
		tree[index] = Math.min(tree[2 * index], tree[2 * index + 1]);
	}

	public static void updateRange(int[] tree, int ss, int se, int l, int r, int inc, int index) {
		if (l > se || r <ss) return;

		if (ss == se) {
			tree[index] += inc;
			return;
		}

		int mid = (ss + se) / 2;
		updateRange(tree, ss, mid, l, r, inc, 2 * index);
		updateRange(tree, mid + 1, se, l, r, inc, 2 * index + 1);
		tree[index] = Math.min(tree[2 * index], tree[2 * index + 1]);

		return;
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		// System.out.print("Enter number of elements in the array: ");
		// int n = sc.nextInt();
		int n = 6;
		// int[] arr = new int[n];
		// for (int i = 0; i < n; i++) {
			// arr[i] = sc.nextInt();
		// }
		int[] arr = {1, 3, 2, -5, 6, 4};
		int[] tree = new int[(4 * n) + 1];

		buildTree(arr, 0, arr.length - 1, tree, 1);
		for (int i = 0; i < tree.length; i++) {
			System.out.print(tree[i] + " ");
		}
		System.out.println();

		updateRange(tree, 0, arr.length - 1, 3, 5, 10, 1);

		// for (int i = 0; i < tree.length; i++) {
		// 	System.out.print(tree[i] + " ");
		// }
		// System.out.println();

		System.out.println("Number of queries: ");
		int q = sc.nextInt();
		while (q != 0) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			int output = query(tree, 0, arr.length - 1, s, e, 1);
			System.out.println(output);
			q--;
		}
	}

}