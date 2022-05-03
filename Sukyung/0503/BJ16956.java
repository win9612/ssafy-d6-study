package day0503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16956 {
	static int R, C;
	static char[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static boolean eat;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		eat = false;

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (eat)
					break;
				if (map[i][j] == 'W') {
					makeFence(i, j);
				}
			}
		}
		nowPasture();
	}

	public static void nowPasture() {
		if (eat) {
			System.out.println(0);
		} else {
			System.out.println(1);
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					System.out.printf("%c", map[i][j]);
				}
				System.out.println();
			}
		}
	}

	public static void makeFence(int i, int j) {
		for (int d = 0; d < 4; d++) {
			int nexti = dx[d] + i;
			int nextj = dy[d] + j;

			if (nexti < 0 || nexti >= R || nextj < 0 || nextj >= C)
				continue;

			if (map[nexti][nextj] == '.') {
				map[nexti][nextj] = 'D';
			}
			if (map[nexti][nextj] == 'S') {
				eat = true;
				return;
			}
		}
	}
}
