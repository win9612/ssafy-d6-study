package Day0308;

import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon1197_최소스패닝트리 {
// 간선을 표현할 내부 클래스 생성
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static int V, E;
	static Edge[] edgelist;
	static int[] parents;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();

		edgelist = new Edge[E];
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			edgelist[i] = new Edge(from, to, weight); // 배열에 객체 생성하며 저장
		}

		Arrays.sort(edgelist); // 간선 비용 오름차순 정렬

		makeSet();

		int result = 0;
		int cnt = 0;

		for (Edge edge : edgelist) {
			if (union(edge.from, edge.to)) {
				result += edge.weight;
				cnt++;
				if (cnt == V - 1)
					break;
			}
		}
		System.out.println(result);

	}

	static void makeSet() {
		parents = new int[V+1];
		for (int i = 1; i <= V; i++) {
			parents[i] = i; // 자신의 부모를 자신으로 설정
		}
	}

	static int findSet(int a) {
		if (a == parents[a]) {
			return a;
		}
		// 내 부모의 부모를 찾아서 root 까지
		return parents[a] = findSet(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) {
			return false;
		}
		parents[bRoot] = aRoot;
		return true;
	}

}