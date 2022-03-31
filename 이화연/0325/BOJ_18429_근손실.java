import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18429_근손실 {
	static int N, K, A[], ans;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N일
		K = Integer.parseInt(st.nextToken()); // 하루 지날때 마다 감소하는 중량
		ans = 0;

		visited = new boolean[N]; // 방문체크
		A = new int[N]; // 각 운동 키트 중량 증가량
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		permu(0, 500);
		System.out.println(ans);
	}

	public static void permu(int cnt, int w) {
		if (cnt == N) ans++;
		if (w < 500) return;

		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;

			visited[i] = true;
			permu(cnt + 1, w + A[i] - K);
			visited[i] = false;
		}
	}

}
