import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1325_효율적인해킹 {
	static int N, M, cnt[];
	static List<Integer>[] list;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 1~N개의 컴퓨터
		M = Integer.parseInt(st.nextToken()); // 신뢰하는 관계 입력 횟수

		list = new LinkedList[N + 1];
		cnt = new int[N + 1];
		visited = new boolean[N + 1];
		for (int n = 1; n <= N; n++) {
			list[n] = new LinkedList<Integer>(); // 초기화
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			// from이 to를 신뢰 = to 해킹하면 from도 해킹 가능
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
		}

		// 컴퓨터 모두 탐색하기
		for (int n = 1; n <= N; n++) {
			if (list[n].size() != 0) { // 해당 정점에 인접해있는 게 없으면 탐색할 필요 없음..
				dfs(n);
			}
		}

		int max = -1;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, cnt[i]); // 가장 큰 값 찾기
		}

		for (int i = 1; i <= N; i++) {
			if (max == cnt[i]) { // 최댓값이랑 같은 것만 출력
				System.out.print(i + " ");
			}
		}

	}

	public static void dfs(int n) {
		visited[n] = true;
		for (int i = 0; i < list[n].size(); i++) {
			if (!visited[list[n].get(i)]) {
				cnt[list[n].get(i)]++;
				dfs(list[n].get(i));
			}
		}
	}
}
