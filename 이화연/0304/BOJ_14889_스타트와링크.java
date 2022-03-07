import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {
	static int N, map[][], min;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // N명

		map = new int[N][N];
		visited = new boolean[N];
		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		comb(0, 0);
		System.out.println(min);
	}

	public static void comb(int idx, int cnt) {
		if (cnt == N / 2) { // N/2명 골랐을 때 리턴
			min = Math.min(min, Math.abs(add()));
			return;
		}

		if (idx == N) {
			return;
		}

		visited[idx] = true;
		comb(idx + 1, cnt + 1);
		visited[idx] = false;
		comb(idx + 1, cnt);
	}

	public static int add() {
		int start =0, link = 0;
		for (int i = 0; i < N; i++) {
			for(int j=i; j<N; j++) {
				if(visited[i] && visited[j]) {
					start += map[i][j] + map[j][i];
				}
				if(!visited[i] && !visited[j]) {
					link += map[i][j] + map[j][i];						
				}
			}
		}
		return Math.abs(start-link);
	}

}
