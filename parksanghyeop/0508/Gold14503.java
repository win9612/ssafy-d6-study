package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gold14503 {

	static int N, M, r, c, d;
	static int[][] arr;
	static int result = 1;
	static int[] dr = { -1, 0, 1, 0 }; // 북 동 남 서
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int val = Integer.parseInt(st.nextToken());
				if (val == 1)
					val = -1;
				arr[i][j] = val;
			}
		}
		find(r, c, d);
		System.out.println(result);
	}

	public static void find(int i, int j, int dir) {
		arr[i][j] = 1;

		for (int d = 0; d < 4; d++) {
			dir = (dir + 3) % 4; // 왼쪽방향으로 회전
			int nr = i + dr[dir];
			int nc = j + dc[dir];

			if (0 <= nc && nc < M && 0 <= nr && nr < N && arr[nr][nc] == 0) {
				result++;
				find(nr, nc, dir);
				return;
			}
		}

		// 반복문을 빠져나왔다는건 후진해야한다는것
		int back = (dir + 2) % 4; // 후진으로 방향 변경
		int br = i + dr[back];
		int bc = j + dc[back];
		
		if (0 <= bc && bc < M && 0 <= br && br < N && arr[br][bc] != -1)
			find(br, bc, dir);
	}

}
