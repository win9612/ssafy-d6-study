import java.io.*;
import java.util.*;

// 나만 안되는 연애
// https://www.acmicpc.net/problem/14621

public class BJ14621 {

	private static char[] gender;
	private static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = parse(st.nextToken()); // 학교수 = 정점수
		int m = parse(st.nextToken()); // 도로수 = 간선수

		makeSet(n);
		gender = (" " + br.readLine().replaceAll(" ", "")).toCharArray();

		PriorityQueue<University> pq = new PriorityQueue<>();
		// 연결정보 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = parse(st.nextToken());
			int to = parse(st.nextToken());
			int weight = parse(st.nextToken());
			if (gender[from] != gender[to])
				pq.offer(new University(from, to, weight));
		}

		// KRUSCAL 알고리즘 수행
		int ans = 0;
		while (n > 0 && !pq.isEmpty()) {
			University u = pq.poll();
			if (union(u.from, u.to)) {
				ans += u.weight;
				n--;
			}
		}

		if (n == 1)
			System.out.println(ans);
		else
			System.out.println("-1");
	}

	private static void makeSet(int n) {
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++)
			parent[i] = i;
	}

	private static int find(int a) {
		if (a == parent[a])
			return a;
		else
			return parent[a] = find(parent[a]);
	}

	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return false;
		parent[pb] = pa;
		return true;
	}

	static class University implements Comparable<University> {
		int from, to, weight;

		University(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(University u) {
			return weight - u.weight;
		}
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
