import java.util.Scanner;

class QuerySegmentTree {

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