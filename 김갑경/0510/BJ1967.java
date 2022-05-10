import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1967
public class BJ1967 {

	private static int ans;
	private static ArrayList<Node>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = parse(br.readLine());
		adj = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		while (n-- > 1) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			adj[parse(st.nextToken())].add(new Node(parse(st.nextToken()), parse(st.nextToken())));
		}

		go(1);
		System.out.println(ans);
	}

	private static int go(int node) {
		int[] max = new int[2];
		for (Node next : adj[node]) {
			max[0] = Math.max(max[0], go(next.n) + next.weight);
			Arrays.sort(max);
		}

		ans = Math.max(ans, max[0] + max[1]);

		return max[1];
	}

	private static class Node {
		int n, weight;

		Node(int n, int weight) {
			this.n = n;
			this.weight = weight;
		}
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
