import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/17073
// 나무 위의 빗물 - BFS 풀이
public class BJ17073 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = parse(st.nextToken()); // 총 노드의 수
		int w = parse(st.nextToken()); // 1번 노드에 고인 물의 양

		ArrayList<Integer>[] adj = new ArrayList[n + 1]; // 인접 리스트
		boolean[] visit = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<Integer>(); // 초기화
		}

		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = parse(st.nextToken());
			int v2 = parse(st.nextToken());

			adj[v1].add(v2);
			adj[v2].add(v1);
		}
		adj[1].add(1);

		double totWater = 0;
		int leafCnt = 0;

		double[] water = new double[n + 1];
		water[1] = w;

		Queue<Integer> q = new LinkedList<>(); // bfs를 위한 큐
		q.add(1); // root=1
		visit[1] = true;

		while (!q.isEmpty()) {
			int currentRoot = q.poll();
			if (adj[currentRoot].size() <= 1) {
//				System.out.println("리프노드" + currentRoot + ", 물양: " + water[currentRoot]);
				// 리프노드이다
				totWater += water[currentRoot];
				leafCnt++;
				continue;
			}

			double waterAmount = water[currentRoot] / (adj[currentRoot].size() - 1);
			water[currentRoot] = 0;
			for (int child : adj[currentRoot]) {
				if (visit[child])
					continue;

				visit[child] = true;
				q.add(child);
				water[child] = waterAmount;
			}
		}

		System.out.println(totWater / leafCnt);

	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}



// 배열 인덱스를 활용한 풀이
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = parse(st.nextToken()); // 총 노드의 수
		int w = parse(st.nextToken()); // 1번 노드에 고인 물의 양

		int[] degree = new int[n + 1];

		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			degree[parse(st.nextToken())]++;
			degree[parse(st.nextToken())]++;
		}

		int leafCnt = 0;
		for (int i = 2; i <= n; i++) {
			if (degree[i] == 1) {
				leafCnt++;
			}
		}

		System.out.println((double) w / leafCnt);

	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
