import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class BOJ_1062_가르침 {
	static int N, K, max;
	static String[] input;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		max = -1;

		if (K - 5 < 0) { // 이미 가지고 있는 단어는 5개이기 때문에 그거보다 더 적으면 아무 단어도 읽을 수 없음
			System.out.println(0);
			return;
		} else if (K == 26) { // 모든 단어 다 읽을 수 있음
			System.out.println(N);
			return;
		}

		input = new String[N];
		for (int s = 0; s < N; s++) {
			input[s] = br.readLine().replace("a", "").replace("n", "").replace("t", "").replace("i", "").replace("c",
					""); // a,n,t,i,c 제거
		}

		visited = new boolean[26];
		visited['a' - 'a'] = true; // a,n,t,i,c 방문 처리
		visited['n' - 'a'] = true;
		visited['t' - 'a'] = true;
		visited['i' - 'a'] = true;
		visited['c' - 'a'] = true;

		combi(0, 0);

		System.out.println(max);
	}

	public static void combi(int cnt, int idx) { // K-5개 뽑기
		if (cnt == (K - 5)) { 
			int count = 0;
			for (int i = 0; i < input.length; i++) {
				boolean flag = true;
				for (int j = 0; j < input[i].length(); j++) {
					if (!visited[input[i].charAt(j) - 'a']) { // 남아있는 단어가 false이면 읽을 수 없음
						flag = false;
						break;
					}
				}
				if (flag) // 남아있는 단어를 읽을 수 있음
					count++;
			}
			max = Math.max(max, count);
			return;
		}

		if (idx >= 26) {
			return;
		}

		if (!visited[idx]) {
			visited[idx] = true;
			combi(cnt + 1, idx + 1);
			visited[idx] = false;
		}

		combi(cnt, idx + 1);
	}

}
