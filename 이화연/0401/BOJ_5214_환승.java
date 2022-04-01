import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5214_환승 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 역의 수
		int K = Integer.parseInt(st.nextToken()); // 서로 연결하는 역 개수
		int M = Integer.parseInt(st.nextToken()); // 하이퍼튜브 개수

		ArrayList<Integer>[] station = new ArrayList[N + M + 1]; // 역의 수 + 하이퍼튜브 수 + 1
		int[] count = new int[N + M + 1];

		for (int i = 1; i < station.length; i++) {
			station[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < M; i++) {
			int temp = N + i + 1;
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < K; j++) {
				int num = Integer.parseInt(st.nextToken());
				station[num].add(temp); // 해당 역에 하이퍼튜브 번호 넣기
				station[temp].add(num); // 해당 하이퍼튜브에 역 넣기
			}
		}

		boolean[] visited = new boolean[N + M + 1];
		Arrays.fill(visited, false);
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1);
		count[1] = 1; // 1번이 시작역
		visited[1] = true;
		while (!queue.isEmpty()) {
			int now = queue.poll();

			if (now == N) { // N번 역에 도착하면
				break;
			}

			for (int n : station[now]) {
				if (!visited[n]) {
					queue.offer(n);
					visited[n] = true;
					// 하이퍼튜브 방문 횟수는 세면 안되기 때문에 하이퍼튜브에서 역으로 가는 경우만 +1
					count[n] = now > N ? count[now] + 1 : count[now];
				}
			}
		}
		System.out.println(visited[N] ? count[N] : -1); // 방문안했으면 -1

	}

}
