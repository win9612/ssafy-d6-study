import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/18428
public class BJ18428 {

	private static List<Point> teachers = new ArrayList<>();
	private static boolean flag;
	private static int n;
	private static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = parse(br.readLine());
		map = new char[n][n];

		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().replaceAll(" ", "").toCharArray();
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 'T') {
					teachers.add(new Point(i, j));
				}
			}
		}

		comb(0, 0);

		System.out.println(flag ? "YES" : "NO");

	}

	private static void comb(int cnt, int idx) {
		if (flag) {
			return;
		}

		if (cnt >= 3) {
			if (gamsi()) {
				flag = true;
			}
			return;
		}

		if (idx >= n * n) {
			return;
		}

		comb(cnt, idx + 1);
		int i = idx / n;
		int j = idx % n;
		if (map[i][j] == 'X') {
			// 빈 칸만 벽 세우기 가능
			map[i][j] = 'W';
			comb(cnt + 1, idx + 1);
			map[i][j] = 'X';
		}
	}

	private static boolean gamsi() {
		for (Point teacher : teachers) {
			for (int dir = 0; dir < 4; dir++) {
				if (!watch(teacher, dir)) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean watch(Point p, int dir) {
		final int[][] move = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		int i = p.i + move[dir][0];
		int j = p.j + move[dir][1];
		while (checkIdx(i, j)) {
			if (map[i][j] == 'S') {
				return false;
			} else if (map[i][j] == 'W') {
				return true;
			}

			i += move[dir][0];
			j += move[dir][1];
		}
		return true;
	}

	private static boolean checkIdx(int i, int j) {
		return i >= 0 && j >= 0 && i < n && j < n;
	}

	private static class Point {
		int i, j;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
