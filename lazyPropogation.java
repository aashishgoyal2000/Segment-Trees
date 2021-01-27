import java.util.Scanner;

class lazyPropogation {

	static int[] lazy = new int[1000];

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

	public static void lazyUpdate(int[] tree, int ss, int se, int l, int r, int inc, int index) {

		// before going down resolve the value if it exists
		if (lazy[index] != 0) {
			tree[index] += lazy[index];
			// non leaf node
			if (ss != se) {
				lazy[2 * index] += lazy[index];
				lazy[2 * index + 1] += lazy[index];
			}
			lazy[index] = 0; // clear the lazy value at current node
		}
		// base case - no overlap
		if (ss > r || l > se) {
			return;
		}

		// complete overlap
		if (ss >= l && se <= r) {
			tree[index] += inc;

			if (ss != se) {
				lazy[2 * index] += inc;
				lazy[2 * index + 1] += inc;
			}
			return;
		}

		// partiaal overlap
		int mid = (ss + se) / 2;
		lazyUpdate(tree, ss, mid, l, r, inc, 2 * index);
		lazyUpdate(tree, mid + 1, se, l, r, inc, 2 * index + 1);

		tree[index] = Math.min(tree[2 * index], tree[2 * index + 1]);
		return;

	}

	public static int queryLazy(int[] tree, int ss, int se, int qs, int qe, int index) {
		// resolve id lazy value exixts
		if (lazy[index] != 0) {
			tree[index] += lazy[index];
			// non leaf node
			if (ss != se) {
				lazy[2 * index] += lazy[index];
				lazy[2 * index + 1] += lazy[index];
			}
			lazy[index] = 0; // clear the lazy value at current node
		}
		// no overlap
		if (ss > qe || se < qs) {
			return Integer.MAX_VALUE;
		}
		// complete overlap
		if (ss >= qs && se <= qe) {
			return tree[index];
		}

		int mid = (ss + se) / 2;
		int left = queryLazy(tree, ss, mid, qs, qe, 2 * index);
		int right = queryLazy(tree, mid + 1, se, qs, qe, 2 * index + 1);

		return Math.min(left, right);

	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = 6;
		int[] arr = {1, 3, 2, -5, 6, 4};
		int[] tree = new int[(4 * n) + 1];

		buildTree(arr, 0, arr.length - 1, tree, 1);
		for (int i = 0; i < tree.length; i++) {
			System.out.print(tree[i] + " ");
		}
		System.out.println();

		lazyUpdate(tree, 0, arr.length - 1, 0, 2, 10, 1);
		lazyUpdate(tree, 0, arr.length - 1, 0, 4, 10, 1);

		System.out.println("Number of queries: ");
		int q = sc.nextInt();
		while (q != 0) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			int output = queryLazy(tree, 0, arr.length - 1, s, e, 1);
			System.out.println(output);
			q--;
		}

		
	}

}