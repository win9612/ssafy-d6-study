package day0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14503 {
	static int N, M, nowi, nowj, direction, count, answer;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { -1, 0, 1, 0 }; // 북 동 남 서
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];

		st = new StringTokenizer(br.readLine(), " ");
		nowi = Integer.parseInt(st.nextToken());
		nowj = Integer.parseInt(st.nextToken());
		direction = Integer.parseInt(st.nextToken());
		count = 0;
		answer = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		clean();
		System.out.println(answer);
	}

	public static void clean() {
		if (map[nowi][nowj] == 0) {
			answer++;
			visit[nowi][nowj] = true;
		}
		while (true) {
			if (count == 4) {
				if (!checkBoundary(nowi - dx[direction], nowj - dy[direction])
						|| map[nowi - dx[direction]][nowj - dy[direction]] == 1) {
					break;
				}
				nowi -= dx[direction];
				nowj -= dy[direction];
				count = 0;
			}
			int left = direction - 1 >= 0 ? direction - 1 : 3;

			int nexti = nowi + dx[left];
			int nextj = nowj + dy[left];
			direction = left;

			if (checkBoundary(nexti, nextj) && isMove(nexti, nextj)) {
				nowi = nexti;
				nowj = nextj;
				answer++;
				count = 0;
				visit[nexti][nextj] = true;
			}
		}
	}

	public static boolean checkBoundary(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M)
			return true;
		count++;
		return false;
	}

	public static boolean isMove(int r, int c) {
		if (visit[r][c] || map[r][c] == 1) {
			count++;
			return false;
		}
		return true;
	}
}
