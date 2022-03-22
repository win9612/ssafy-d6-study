import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/16918
// 봄버맨
public class BJ16918 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()); // 세로
		int c = Integer.parseInt(st.nextToken()); // 가로
		int n = Integer.parseInt(st.nextToken()); // 시간
		int[][] map = new int[r][c];

		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				if (s.charAt(j) == '.') {
					map[i][j] = 0;
				} else {
					map[i][j] = 2;
				}
			}
		}

		n--;
		final int[][] move = { { 0, 0 }, { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		Queue<Point> q = new LinkedList<>();
		while (n-- > 0) {
			// 1. 폭탄이 설치되어있지 않은 칸에 폭탄을 설치한다.
			// 2. 폭탄 카운트를 증가시킨다.
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					map[i][j]++;
					if (map[i][j] >= 4) {
						q.add(new Point(i, j));
					}
				}
			}

			// 값이 3인 칸이 모두 폭발한다.
			while (!q.isEmpty()) {
				Point p = q.poll();

				for (int i = 0; i < 5; i++) {
					int ni = p.i + move[i][0];
					int nj = p.j + move[i][1];

					if (ni < 0 || nj < 0 || ni >= r || nj >= c)
						continue;

					map[ni][nj] = 0;
				}
			}
		}
		print(map);

	}

	private static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] <= 0) {
					System.out.print('.');
				} else {
					System.out.print('O');
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private static class Point {
		int i, j;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
