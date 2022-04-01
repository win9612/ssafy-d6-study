import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14923
// 미로 탈출
public class BJ14923 {

	private static final int[][] move = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = parse(st.nextToken()); // 세로
		int m = parse(st.nextToken()); // 가로
		st = new StringTokenizer(br.readLine());
		Point start = new Point(parse(st.nextToken()) - 1, parse(st.nextToken()) - 1);
		st = new StringTokenizer(br.readLine());
		Point end = new Point(parse(st.nextToken()) - 1, parse(st.nextToken()) - 1);

		char[][] map = new char[n][m];
		boolean[][][] visit = new boolean[n][m][2];

		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().replaceAll(" ", "").toCharArray();
		}

		Queue<Point> q = new LinkedList<>();

		q.offer(start);
		visit[start.i][start.j][0] = true;
		int cnt = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int s = 0; s < size; s++) {
				Point p = q.poll();

				if (p.equals(end)) {
					System.out.println(cnt);
					return;
				}

				for (int i = 0; i < move.length; i++) {
					int ni = p.i + move[i][0];
					int nj = p.j + move[i][1];

					if (ni >= n || nj >= m || ni < 0 || nj < 0)
						continue;

					if (map[ni][nj] == '1') {
						if (!visit[ni][nj][1] && p.wall == 0) {
							q.offer(new Point(ni, nj, p.wall + 1));
							visit[ni][nj][1] = true;
						}
						continue;
					} else if (!visit[ni][nj][p.wall]) {
						q.offer(new Point(ni, nj, p.wall));
						visit[ni][nj][p.wall] = true;
					}
				}
			}
			cnt++;
		}
		System.out.println(-1);
	}

	private static class Point {
		int i, j, wall;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

		Point(int i, int j, int wall) {
			this.i = i;
			this.j = j;
			this.wall = wall;
		}

		@Override
		public boolean equals(Object o) {
			Point p = (Point) o;
			return i == p.i && j == p.j;
		}
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
