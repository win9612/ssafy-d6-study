import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1197
// BJ1197: 최소 스패닝 트리
public class BJ1197 {

	private static int[] p;
	private static int v, e;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		v = parse(st.nextToken()); // 정점
		e = parse(st.nextToken()); // 간선

		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new Node(parse(st.nextToken()), parse(st.nextToken()), parse(st.nextToken())));
		}

		makeSet();

		int ans = 0;
		while (!pq.isEmpty() && v > 0) {
			Node n = pq.poll();
			if (union(n.from, n.to)) {
				ans += n.weight;
				v--;
			}
		}
		System.out.println(ans);
	}

	private static void makeSet() {
		p = new int[v + 1];
		for (int i = 1; i <= v; i++) {
			p[i] = i;
		}
	}

	private static int find(int a) {
		if (p[a] == a)
			return a;
		else
			return p[a] = find(p[a]);
	}

	private static boolean union(int a, int b) {
		int pa = find(p[a]);
		int pb = find(p[b]);
		if (pa == pb)
			return false;
		p[pb] = pa;
		return true;
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}

	private static class Node implements Comparable<Node> {
		int from, to, weight;

		Node(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node n) {
			return weight - n.weight;
		}
	}
}
