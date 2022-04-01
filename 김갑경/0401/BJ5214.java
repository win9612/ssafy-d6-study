import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/5214
// 환승
public class BJ5214 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = parse(st.nextToken()); // 정점의 수 최대 10만
		int k = parse(st.nextToken()); // 하이퍼튜브 하나로 연결하는 지점의 개수
		int m = parse(st.nextToken()); // 하이퍼튜브 개수

		boolean[] visitNode = new boolean[n + 1];
		boolean[] visitTube = new boolean[m + 1];
		int[][] station = new int[m][k];
		List<Integer>[] adj = new ArrayList[n + 1]; // i번째 수가 있는 하이퍼튜브의 번호를 저장
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		while (m-- > 0) { // 하이퍼튜브
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < k; i++) {
				station[m][i] = parse(st.nextToken());
				adj[station[m][i]].add(m);
			}
		}

		Queue<Integer> q = new LinkedList<>(); // bfs를 위한 큐

		q.add(1);
		visitNode[1] = true;

		int t = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				int now = q.poll();
				if (now == n) {
					System.out.println(t);
					return;
				}

				// 현재 점이 있는 하이퍼튜브를 모두 탐색
				for (int nextTube : adj[now]) {
					if (visitTube[nextTube])
						continue;

					visitTube[nextTube] = true;
					// 그 점이 있는 하이퍼튜브를 탐색하여 큐에 넣는다

					for (int next : station[nextTube]) {
						if (visitNode[next])
							continue;

						visitNode[next] = true;
						q.offer(next);
					}
				}
			}
			t++;
		}

		System.out.println(-1);

	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
