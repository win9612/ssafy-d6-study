import java.io.*;
import java.util.*;

// 데스 나이트
// https://www.acmicpc.net/problem/16948
public class BJ16948 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = parse(br.readLine()); // 체스판의 크기
		StringTokenizer st = new StringTokenizer(br.readLine());
		Point start = new Point(parse(st.nextToken()), parse(st.nextToken()));
		Point end = new Point(parse(st.nextToken()), parse(st.nextToken()));
		boolean[][] map = new boolean[n][n];

		final int[][] move = { { -2, -1 }, { -2, 1 }, { 0, -2 }, { 0, 2 }, { 2, -1 }, { 2, 1 } };
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		map[start.r][start.c] = true;
		int cnt = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Point p = q.poll();
				if (p.equals(end)) {
					System.out.println(cnt);
					return;
				}

				for (int i = 0; i < move.length; i++) {
					int nr = p.r + move[i][0];
					int nc = p.c + move[i][1];

					if (nr >= n || nc >= n || nr < 0 || nc < 0 || map[nr][nc])
						continue;

					map[nr][nc] = true;
					q.offer(new Point(nr, nc));
				}
			}
			cnt++;
		}

		System.out.println(-1);

	}

	private static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public boolean equals(Object obj) {
			Point p = (Point) obj;
			return r == p.r && c == p.c;
		}
	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
