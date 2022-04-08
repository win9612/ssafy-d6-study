import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/19238
// 스타트 택시 

public class BJ19238 {

	private static int n, m, fuel;
	private static int[][] map;
	private static final int[][] move = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static Point start;
	private static Point[] destPoint;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = parse(st.nextToken()); // 행
		m = parse(st.nextToken()); // 열
		fuel = parse(st.nextToken()); // 초기연료
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = parse(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		start = new Point(parse(st.nextToken()) - 1, parse(st.nextToken()) - 1);
		destPoint = new Point[m];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			map[parse(st.nextToken()) - 1][parse(st.nextToken()) - 1] = i + 2;
			destPoint[i] = new Point(parse(st.nextToken()) - 1, parse(st.nextToken()) - 1);
		}

		while (m-- > 0 && fuel >= 0) {
			// 1. 가장 가까운 손님의 번호 찾기
			int destPassenger = getPassenger();

			if (destPassenger == -1) {
				System.out.println(-1);
				return;
			}
			map[start.i][start.j] = 0;

			// 2. 해당 손님의 목적지까지 이동
			fuel = moveToPassengerDest(destPoint[destPassenger]);
			start = destPoint[destPassenger];
		}
		System.out.println(fuel);
	}

	private static int moveToPassengerDest(Point dest) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[n][n];
		q.offer(start);
		visit[start.i][start.j] = true;

		int dist = 0;
		while (!q.isEmpty() && fuel >= 0) {
			int size = q.size();
			while (size-- > 0) {
				Point p = q.poll();

				if (p.i == dest.i && p.j == dest.j) {
					return fuel + dist * 2;
				}

				for (int d = 0; d < 4; d++) {
					int ni = p.i + move[d][0];
					int nj = p.j + move[d][1];

					if (ni < 0 || nj < 0 || ni >= n || nj >= n || map[ni][nj] == 1 || visit[ni][nj])
						continue;

					q.offer(new Point(ni, nj));
					visit[ni][nj] = true;
				}
			}
			fuel--;
			dist++;
		}

		return -1;
	}

	private static int getPassenger() {
		Queue<Point> q = new LinkedList<>();
		PriorityQueue<Point> pq = new PriorityQueue<>();
		boolean[][] visit = new boolean[n][n];
		q.offer(start);

		while (!q.isEmpty() && fuel >= 0) {
			int size = q.size();
			while (size-- > 0) {
				Point p = q.poll();

				if (map[p.i][p.j] >= 2) {
					p.num = map[p.i][p.j] - 2;
					pq.offer(p);
				}

				for (int d = 0; d < 4; d++) {
					int ni = p.i + move[d][0];
					int nj = p.j + move[d][1];

					if (ni < 0 || nj < 0 || ni >= n || nj >= n || map[ni][nj] == 1 || visit[ni][nj])
						continue;

					q.offer(new Point(ni, nj));
					visit[ni][nj] = true;
				}
			}
			if (pq.size() > 0) {
				start = new Point(pq.peek().i, pq.peek().j);
				return pq.poll().num;
			}
			fuel--;
		}

		return -1;
	}

	private static class Point implements Comparable<Point> {
		int i, j, num;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public int compareTo(Point o) {
			return (i == o.i) ? j - o.j : i - o.i;
		}

	}

	private static int parse(String s) {
		return Integer.parseInt(s);
	}
}
